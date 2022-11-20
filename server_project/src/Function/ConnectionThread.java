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

	// Flag
	private volatile boolean exit = false;

	public ConnectionThread() {
		this.server = null;
		this.client = null;
		this.ConnectionState = state.UNCONNECTED;
		this.exit = false;
	}

	public state getConnectionState() {
		return this.ConnectionState;
	}

	public void stopConnection() {
		this.exit = true;
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

				ClientThread woker = new ClientThread(client);
				woker.start();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		try {
			server.close();
		} catch (Exception e2) {
			e2.getStackTrace();
		}
	}

}
