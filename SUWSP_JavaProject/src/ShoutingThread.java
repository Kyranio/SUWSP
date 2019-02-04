

import java.net.*;
import java.io.*;
//import java.util.concurrent.*;

class Worker implements Runnable
{
	private Socket connection;

	public Worker(Socket connection) {
		this.connection = connection;
	}

	public void run() {
		try {
			String s;
			System.err.println("New worker thread started");
			
			//lets check if we already accepted maximum number of connections
			ShoutingMTServer.mijnSemafoor.probeer();
			
			FileWriter fileWriter = new FileWriter("E:\\OneDrive\\OneDrive - Hanzehogeschool Groningen\\Documenten\\------------- Jaar 2 -------------\\Project\\Generator\\OutputTest.xml");

			BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((s = bin.readLine()) != null) {
	            System.out.println(s);
			    fileWriter.write(s);
	        }
			
			// now close the socket connection
			connection.close();
		    fileWriter.close();
			System.err.println("Connection closed: workerthread ending");
			// upping the semaphore.. since the connnection is gone....
			ShoutingMTServer.mijnSemafoor.verhoog();
		}
		catch (IOException ioe) { }
		catch (InterruptedException ie) {}
	}
}
