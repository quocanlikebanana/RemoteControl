package Application;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {
	private String host;
	private int port;
	private Main_Window main;

	public ClientConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void setMain_Window(Main_Window mw) {
		this.main = mw;
	}
	
	public boolean checkStartConnection() throws IOException {
		Socket client = new Socket(this.host, this.port);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		oos.writeObject("START");
		oos.flush();
		boolean res = client.isConnected();
		client.close();

		// Check connection constantly
		CheckThread cth = new CheckThread(this);
		cth.start();
		return res;
	}
	
	public boolean checkConnection() throws IOException {
		Socket client = new Socket(this.host, this.port);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		oos.writeObject("CHECK");
		oos.flush();
		boolean res = client.isConnected();
		client.close();
		return res;
	}
	
	public void endConnection(boolean passive) throws IOException {
		// Intend to end
		if (passive == false) {
			System.out.println("2.1");
			Socket client = new Socket(this.host, this.port);
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject("END");
			oos.flush();
			System.out.println("2.1");
			client.close();
		}
		// Forced to end
		else {
			//
			System.out.println("2.2");
		}
		this.main.returnToConnectionTab();
	}

}
