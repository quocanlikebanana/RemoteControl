package App;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ListApp {
	private Socket socket =  null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public ListApp(String host,int port) throws IOException {
		this.socket = new Socket(host,port);
		this.ois = new ObjectInputStream(socket.getInputStream());
		this.oos = new ObjectOutputStream(socket.getOutputStream());
	
	}
	public void sendRequest() throws IOException {
		oos.writeObject("GET_APPLICATION_LIST");
		oos.flush();
	}
	public Object getResonseData() throws ClassNotFoundException, IOException {
		return ois.readObject();
	}
}
