package KeyLogger;




import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class KeyLoggerDelete {


	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public KeyLoggerDelete(String host,int port) {
		try {
			this.socket = new Socket(host,port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public void sendRequest() {
		try {
			oos.writeObject("DELETE_KEY");
			oos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	

}
