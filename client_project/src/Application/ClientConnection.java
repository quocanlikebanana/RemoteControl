package Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {
	private String host;
	private int port;
	private ClientApplication_GUI main;
	private CheckThread cth;

	public ClientConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void setMain_Window(ClientApplication_GUI mw) {
		this.main = mw;
	}
	
	public boolean checkStartConnection() throws IOException {
		Socket client = new Socket(this.host, this.port);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		oos.writeObject("START");
		oos.flush();
		boolean res = client.isConnected();
		client.close();
		return res;
	}
	
	
	public void checkConnectionConstantly() {
		// Check connection constantly
		cth = new CheckThread(this);
		cth.start();
	}
	
	public boolean checkConnection() throws IOException {
		Socket client = new Socket(this.host, this.port);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		oos.writeObject("CHECK");
		oos.flush();
		boolean res = client.isConnected();
		client.close();
		return res;
	}
	
	public void endConnection(boolean passive) throws IOException {
		// Intend to end
			// Come from: CheckThread
			// End the connection
		if (passive == false) {
			Socket client = new Socket(this.host, this.port);
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			oos.writeObject("END");
			oos.flush();
			client.close();
		}
		// Forced to end
			// Come from: User
			// End the CheckThread
		else {

			if (cth != null) {
				cth.stopCheckThread();
			}
		}
		this.main.returnToConnectionTab();
	}

}
