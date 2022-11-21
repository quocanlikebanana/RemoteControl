package Function;

import java.net.ServerSocket;
import java.net.Socket;

import Application.Server_GUI;

public class ClientThread extends Thread {

	private Socket client;
	private ServerSocket server;
	private Server_GUI main;

	private static final int LINGER_TIME = 5000;

	// Flag
	private volatile boolean exit = false;
	private boolean good = true;

	public void stopClientConnection() {
		this.exit = true;
		try {
			this.server.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public ClientThread(Socket client, ServerSocket server, Server_GUI main) {
		if (client == null || server == null || main == null) {
			good = false;
		}
		this.client = client;
		this.server = server;
		this.main = main;
	}

	@Override
	public void run() {
		if (good == false) {
			return;
		}
		try {
			while (!exit) {
				client = server.accept();
				client.setSoLinger(true, LINGER_TIME);

				ActionThread woker = new ActionThread(client, main);
				woker.start();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
