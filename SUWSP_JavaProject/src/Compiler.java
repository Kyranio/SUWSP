import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Compiler{
	public static void main(String argv[]){
    try {
    int DocumentID = 1; 
	File fXmlFile = new File("D:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\Data\\Ouput1");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();	
	
	String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(Calendar.getInstance().getTime());

	NodeList nList = doc.getElementsByTagName("MEASUREMENT");
	FileWriter fileWriter = new FileWriter("D:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\DataCompiled\\"+timeStamp+".SUWSDF");
	
	File dir = new File("D:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\data");
	  File[] directoryListing = dir.listFiles();
	  if (directoryListing != null) {
	    for (File child : directoryListing) {
	      // Do something with child
	    	System.out.println(child);
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
	    		    fileWriter.write("\n");
	    		    System.out.println("Station data toegevoegd aan de Compiled File");
	    		}
	    	}
	    }
	  } else {
	    // Handle the case where dir is not really a directory.
	    // Checking dir.isDirectory() above would not be sufficient
	    // to avoid race conditions with another process that deletes
	    // directories.
	  }
	fileWriter.close();
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}
