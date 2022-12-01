package Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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

	public boolean checkStartConnection() {
		try {
			Socket client = new Socket(this.host, this.port);
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			oos.writeObject("START");
			oos.flush();
			client.close();
			return true;
		} catch (IOException e) {
			System.out.println("ioe checkStartConnection - normal");
			return false;
		}
	}

	public void checkConnectionConstantly() {
		// Check connection constantly
		cth = new CheckThread(this);
		cth.start();
	}

	public boolean checkConnection() {
		try {
			Socket client = new Socket(this.host, this.port);
			if (!client.isConnected()) {
				throw new UnknownHostException();
			}
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			oos.writeObject("CHECK");
			oos.flush();
			client.close();
			return true;
		} catch (UnknownHostException e) {
			System.out.println("uhe checkConnection - normal");
			return false;
		} catch (IOException e) {
			System.out.println("ioe checkConnection - normal");
			return false;
		}
	}

	public void endConnection(boolean serverIsClosed) {
		try {
			// Come from: User turn off the connection -> Disable CheckThread immediately
			if (serverIsClosed == false) {
				if (cth.isRunning()) {
					cth.stopCheckThread();
				}
				// Send END signal to the server
				Socket client = new Socket(this.host, this.port);
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				oos.writeObject("END");
				oos.flush();
				client.close();
			}
			// Come from: CheckThread -> CheckThread will end automatically
			else {
				// Just in case
				if (cth.isRunning()) {
					cth.stopCheckThread();
				}
			}
			this.main.returnToConnectionTab();
		} catch (IOException e) {
			System.out.println("ioe endConnection");
		}
	}

}
