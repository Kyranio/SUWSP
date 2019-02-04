import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileWriter;

public class Compiler {

public static void CompileData(String argv[], File fileToCompile) {
    try {
	File fXmlFile = fileToCompile; //new File("E:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\Generator\\OutputTest.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();	
	
	NodeList nList = doc.getElementsByTagName("MEASUREMENT");
	FileWriter fileWriter = new FileWriter("E:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\Generator\\outputCompiled.txt");
	
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
	fileWriter.close();
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}
