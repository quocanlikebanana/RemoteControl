package Function;

import java.net.ServerSocket;
import java.net.Socket;

import Application.Server_GUI;

public class ConnectionThread extends Thread {

	public static enum state {
		CONNECTED, UNCONNECTED, CORRUPT,
	}

	private ServerSocket server = null;
	private Socket client = null;
	private state ConnectionState;

	private static final int PORT = 8080;
	private static final int LINGER_TIME = 5000;

	public Server_GUI main;

	// Flag
	private volatile boolean exit = false;

	public ConnectionThread(Server_GUI m) {
		this.server = null;
		this.client = null;
		this.ConnectionState = state.UNCONNECTED;
		this.exit = false;
		this.main = m;
	}

	public String getIp() {
		if (this.ConnectionState == state.CONNECTED) {
			return this.client.getInetAddress().toString().substring(1);
		}
		return null;
	}

	public void stopConnection() {
		this.exit = true;
		try {
			this.server.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public void run() {
		if (ConnectionState == state.CONNECTED)
			return;

		ConnectionState = state.CONNECTED;
		try {

			server = new ServerSocket(PORT);

			while (!exit) {
				client = server.accept();
				client.setSoLinger(true, LINGER_TIME);
				main.addToIpList(this.getIp());

				ClientThread woker = new ClientThread(client, main);
				woker.start();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
