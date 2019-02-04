import java.net.ServerSocket;
import java.net.Socket;

public class ShoutingMTServer {
	public static final int PORT = 2500;
	private static final int maxnrofConnections=4;
	public static TelSemafoor mijnSemafoor = new TelSemafoor(maxnrofConnections);
	
 
	public static void main(String[] args) {
		Socket connection;
		int ConnectionID = 0;
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.err.println("MT Server started..bring on the load, to a maximum of: " + maxnrofConnections);
			
			while (true) {
				connection = server.accept();		
				System.err.println("New connection accepted..handing it over to worker thread");
				Thread worker = new Thread(new Worker(connection, ++ConnectionID));
				worker.start();
			}
		}

		catch (java.io.IOException ioe) { }
	}
}