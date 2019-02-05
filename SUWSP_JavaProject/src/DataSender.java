import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DataSender implements Runnable {
    private Thread t;
    public int portNumber = 0;
    DataImporter importer;
    File transferFile;

    public DataSender(String port, File fileToSend) {
        if (port.length() == 0) {
            System.err.println("No port number was specified");
            System.exit(1);
        } else if (fileToSend == null) {
            System.err.println("There was no file attached to the datasender.");
            System.exit(1);
        }

        portNumber = Integer.parseInt(port);
        transferFile = fileToSend;
    }

    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket outSocket = serverSocket.accept();
            OutputStream outStream = outSocket.getOutputStream();

            byte[] fileBytes = new FileInputStream(transferFile).readAllBytes();
            outStream.write(fileBytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String threadName;

    public void start(String name) {
        threadName = name;
        System.out.println("Starting DataSender");
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}


