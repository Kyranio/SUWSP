import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DataImporter implements Runnable {
    private Thread importer;
    public File importFile;

    boolean working;

    public void run() {
        working = true;
        try {
            InputSource iSource = new InputSource();
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            iSource.setCharacterStream(new StringReader("nothing"));

            try {
                Document document = dBuilder.parse(iSource);
                NodeList nodes = document.getElementsByTagName("MEASUREMENT");
                StringBuilder stringBuilder = new StringBuilder();

                int length = nodes.getLength();
                for (int i = 0; i < length; i++) {
                    Element measurement = (Element) nodes.item(i);

                    String[] tags = {"STN", "DATE", "TIME", "DEWP", "STP", "TEMP", "SLP", "VISIB", "WDSP", "PRCP", "SNDP", "FRSHTT", "CLDC", "WNDIR"};
                    HashMap<String, String> measurementData = new HashMap<>();
                    for (String tag : tags) {
                        measurementData.put(tag, measurement.getElementsByTagName(tag).item(0).getTextContent());
                    }

                    //Corrector.Correct();

                    StringBuilder tagStringBuilder = new StringBuilder();
                    tagStringBuilder.append("\n(");

                    for (String tag : tags) {
                        if (tag.equals("DATE") || tag.equals("TIME") || tag.equals("FRSHTT")) {
                            tagStringBuilder.append("'");
                            tagStringBuilder.append(measurementData.get(tag));
                            tagStringBuilder.append("', ");
                        } else if (tag.equals("WNDDIR")) {
                            tagStringBuilder.append(measurementData.get(tag));
                            tagStringBuilder.append("),");
                        } else {
                            tagStringBuilder.append(measurementData.get(tag));
                            tagStringBuilder.append(", ");
                        }
                    }

                    stringBuilder.append(tagStringBuilder);
                }
            } catch (SAXException | IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } catch (ParserConfigurationException parserE) {
            System.out.println(parserE.getMessage());
            parserE.printStackTrace();
        }
        working = false;
    }

    public DataImporter(File fileToImport) {
        importFile = fileToImport;
    }

    private String threadName;

    public void start(String name) {
        threadName = name;
        System.out.println("Starting DataImporter");
        if (importer == null) {
            importer = new Thread (this, threadName);
            importer.start ();
        }
    }
}
