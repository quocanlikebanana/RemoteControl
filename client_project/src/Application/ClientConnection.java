package Application;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {
	private String host;
	private int port;
	private ObjectOutputStream oos;
	private Main_Window main;

	public ClientConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void getMain_Window(Main_Window mw) {
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
		new CheckThread(this);

		return res;
	}
	public boolean checkConnection() throws IOException {
		Socket client = new Socket(this.host, this.port);
		this.oos = new ObjectOutputStream(client.getOutputStream());
		oos.writeObject("CHECK");
		oos.flush();
		boolean res = client.isConnected();
		client.close();
		return res;
	}
	public boolean endConnection(boolean passive) throws IOException {
		// Intend to end
		if (passive == false) {
			Socket client = new Socket(this.host, this.port);
			this.oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject("END");
			oos.flush();
			boolean res = client.isConnected();
			client.close();
			return res;
		}
		// Forced to end
		else {
			//
		}
		this.main.returnToConnectionTab();
		return false;
	}

}
