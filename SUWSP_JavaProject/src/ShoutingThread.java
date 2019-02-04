import java.net.*;
import java.io.*;
//import java.util.concurrent.*;

class Worker implements Runnable
{
	private Socket connection;
	public int threadID;

	public Worker(Socket connection, int threadid ) {
		this.connection = connection;
		this.threadID = threadid;
	}

	public void run() {
		try {
			String s;
			System.err.println("Receiver thread " + threadID + " started.");
			FileWriter fileWriter = new FileWriter("D:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\Data\\Ouput"+threadID+".xml");

			BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((s = bin.readLine()) != null) {
	            System.out.println(s);
			    fileWriter.write(s);
	        }
			
			// now close the socket connection
			connection.close();
		    fileWriter.close();
			System.err.println("Connection closed: workerthread ending");
		}
		catch (IOException ioe) { }
		//catch (InterruptedException ie) {}
	}
}
