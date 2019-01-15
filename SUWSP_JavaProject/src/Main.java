import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String args[]) {

        if (args.length <= 0) {
            System.err.println("Please specify a correct portnumber, and nothing else.");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine.toUpperCase());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
