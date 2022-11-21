package Function;

import java.net.ServerSocket;
import java.net.Socket;

import Application.Server_GUI;


/** 
 * Single Thread
*/

public class ConnectionThread extends Thread {
	
	private ServerSocket server = null;
	private Socket client = null;

	private static final int PORT = 8080;
	private static final int LINGER_TIME = 5000;

	public Server_GUI main;

	// Flag
	private volatile boolean exit = false;

	public ConnectionThread(Server_GUI m) {
		this.server = null;
		this.client = null;
		this.exit = false;
		this.main = m;
	}

	public String getIp() {
		return this.client.getInetAddress().toString().substring(1);
	}

	public void stopConnection() {
		this.exit = true;
		try {
			this.server.close();
		} catch (Exception e) {
			System.out.println("e");
		}
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(PORT);

			while (!exit) {
				client = server.accept();
				client.setSoLinger(true, LINGER_TIME);

				ActionThread woker = new ActionThread(client, main);
				woker.start();
			}
		} catch (Exception e) {
			System.out.println("e");
		}
	}

}
