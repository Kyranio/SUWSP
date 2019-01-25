import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class DataReceiver {
    private Thread t;
    public int portNumber = 0;

    public void dataReceiver(String port) {
        if (port.length() == 0) {
            System.err.println("No port number was specified");
            System.exit(1);
        }

        portNumber = Integer.parseInt(port);
    }

    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);

            while(true) {
                Socket clientSocket = serverSocket.accept();

                InputStream stream = clientSocket.getInputStream();
                byte[] buffer = new byte[stream.available()];
                initialStream.read(buffer);

                File targetFile = new File("src/main/resources/xml.tmp");
                OutputStream outStream = new FileOutputStream(targetFile);
                OutStream.write(buffer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        System.out.println("Starting DataReceiver");
        if (t == null) {
            t = new Thread (this, "DataReceiver");
            t.start ();
        }
    }
}


public class Main {
    public static void main(String args[]) {
        if (args.length <= 0) {
            System.err.println("Please specify a correct portnumber, and nothing else.");
            System.exit(1);
        }

        try {
            DataReceiver dataReceiver = new DataReceiver(args[0]);
            dataReceiver.start();
        } catch {
            System.out.println(e.getMessage());
        }
    }
}