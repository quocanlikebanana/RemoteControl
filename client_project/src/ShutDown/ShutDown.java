package ShutDown;

//import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ShutDown {
	private Socket socket = null;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public ShutDown(String host,int port) {
		try {
			this.socket = new Socket(host,port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public void sendRequest() {
		try {
			oos.writeObject("SHUT_DOWN");
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
