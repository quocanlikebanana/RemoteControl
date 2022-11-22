package App;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AppStart {
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public AppStart(String host,int port) {
		// TODO Auto-generated constructor stub
		try {
			this.socket = new Socket(host,port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	public void sendRequest(String processName) {
		try {
			String request = "START_APPLICATION " + processName;
			oos.writeObject(request);
			oos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public Object getResponseData() {
		try {
			return ois.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			return null;
		}
	}
}
