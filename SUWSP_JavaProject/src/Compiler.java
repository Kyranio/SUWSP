import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Compiler implements Runnable {
	Thread compilerThread;
	String threadName;

	String contentFolder;
	File dir;

	public Compiler(String folderPath) {
		contentFolder = folderPath;
		dir = new File(folderPath);
	}

	public void start(String name) {
		threadName = name;
		System.out.println("Starting Compiler");
		if (compilerThread == null) {
			compilerThread = new Thread(this, threadName);
			compilerThread.start();
		}
	}

	public void run() {
		try {
			while (true) {
				compilerThread.sleep(2500);
				System.out.println("Checking for new content in received files folder");
				if (CheckForNewContent())
					Compile(contentFolder);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	boolean CheckForNewContent() {
		if (dir.isDirectory() || dir.exists()) {
			if (dir.list().length > 0){
				return true;
			} else {
				return false;
			}
		}	else{
			System.out.println("Please check folder, either not a folder or does not exists");
			return false;
		}
	}

  public void Compile(String fileDir) {
	  try {
		  String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(Calendar.getInstance().getTime());

		  File outputFile = new File(System.getProperty("user.dir") + "/export/"+timeStamp+".SUWSDF");
		  FileWriter fileWriter = new FileWriter(outputFile);
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
			  for (File child : directoryListing) {

				  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				  Document doc = dBuilder.parse(child);
				  doc.getDocumentElement().normalize();
				  NodeList nList = doc.getElementsByTagName("MEASUREMENT");

				  for (int temp = 0; temp < nList.getLength(); temp++) {
					  Node nNode = nList.item(temp);
					  if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						  Element eElement = (Element) nNode;
						  NodeList nodeList = eElement.getElementsByTagName("*");
						  for (int i = 0; i < nodeList.getLength(); i++) {
							  Node node = nodeList.item(i);
							  if (node.getNodeType() == Node.ELEMENT_NODE) {
								  String Waarde = node.getTextContent() + "#";

								  // Opslag besparende maatregelen:
								  if(node.getNodeName()=="DATE") {
									  Waarde = Waarde.replace("-", "");
								  }
								  if(node.getNodeName()=="TIME") {
									  Waarde = Waarde.replace(":", "");
								  }
								  if(node.getNodeName()=="WNDDIR") {
									  Waarde = Waarde.replace("#","");
								  }

								  fileWriter.write(Waarde);
							  }
						  }
						  fileWriter.write("_");
					  }
				  }
				  child.delete();
			  }
		  }
		  fileWriter.close();

		  if (outputFile.length() == 0) {
		  	  System.out.println("Output file is empty, deleting empty file...");
		  	outputFile.delete();
		  } else {
			  System.out.println("Compiling done!");
		  }
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
  }
}
