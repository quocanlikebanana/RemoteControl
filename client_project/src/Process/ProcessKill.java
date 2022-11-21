package Process;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ProcessKill {
	private Socket socket =  null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public ProcessKill(String host,int port) throws UnknownHostException, IOException {
		this.socket = new Socket(host,port);
		this.ois = new ObjectInputStream(socket.getInputStream());
		this.oos = new ObjectOutputStream(socket.getOutputStream());	
	}
	
	public void sendRequest(String pID) {
		try {
			String request = "KILL_PROCESS " + pID;
			oos.writeObject(request);
			oos.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public Object getResponseData()  {
		try {
			return ois.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		return null;
	}
}
