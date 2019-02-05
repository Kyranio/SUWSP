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

public class Compiler{

	public static void main(String argv[]){
    try {
	String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(Calendar.getInstance().getTime());

	FileWriter fileWriter = new FileWriter("D:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\DataCompiled\\"+timeStamp+".SUWSDF");
	
	File dir = new File("D:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\data");
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
	System.out.println("Compiling done!");
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}
