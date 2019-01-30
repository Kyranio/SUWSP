import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DataReceiver implements Runnable {
    private Thread t;
    public int portNumber = 0;

    public DataReceiver(String port) {
        if (port.length() == 0) {
            System.err.println("No port number was specified");
            System.exit(1);
        }

        portNumber = Integer.parseInt(port);
    }

    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);

            Socket clientSocket = serverSocket.accept();
            while(serverSocket.isBound()) {
                InputStream stream = clientSocket.getInputStream();
                byte[] buffer = new byte[stream.available()];
                stream.read(buffer);
                System.out.println(stream.available());

                File targetFile = getFile();
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);
                outStream.flush();

                System.out.println("end");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    File getFile() throws IOException {
        try {
            File returnFile = new File("xml.txt");
            returnFile.createNewFile();
            return returnFile;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String threadName;

    public void start(String name) {
        threadName = name;
        System.out.println("Starting DataReceiver");
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}


