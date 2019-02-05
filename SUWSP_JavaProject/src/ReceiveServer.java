import java.net.*;
import java.io.*;
//import java.util.concurrent.*;

class DataReceiveHandler implements Runnable
{
	private Socket connection;
	public int threadID;

	public DataReceiveHandler(Socket connection, int threadid ) {
		this.connection = connection;
		this.threadID = threadid;
	}

	public void run() {
		try {
			String s;
			int h = 0; 
			System.err.println("Receiver thread " + threadID + " started.");
			FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/ReceiveDumpFiles/" +threadID+".xml", false);

			BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((s = bin.readLine()) != null) {
				if(h<=162) { 
			    fileWriter.write(s);
			    h++; 
				} 
				else { 
					fileWriter.close();
//					connection.close();
				}
	        }
			connection.close();
		    fileWriter.close();

			System.out.println("Connection closed: workerthread "+threadID+" ending");
		}
		catch (IOException ioe) { }
		//catch (InterruptedException ie) {}
	}
}
