package Application;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {
	private String host;
	private int port;
	private ObjectOutputStream oos;
	private CheckThread cth;
	
	public ClientConnection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public boolean startConnection() throws IOException {
		Socket client = new Socket(this.host, this.port);
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		oos.writeObject("START");
		oos.flush();
		boolean res = client.isConnected();
		client.close();
		
		// Check connection constantly
		cth = new CheckThread(this);
		
		return res;
	}
	
	public boolean endConnection() throws IOException {
		Socket client = new Socket(this.host, this.port);
		this.oos = new ObjectOutputStream(client.getOutputStream());
		oos.writeObject("END");
		oos.flush();
		boolean res = client.isConnected();
		client.close();
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
	
	
}
