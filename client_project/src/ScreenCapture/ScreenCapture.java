package ScreenCapture;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ScreenCapture {
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public ScreenCapture(String host,int port) {
		try {
			this.socket = new Socket(host,port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public void sendReuqest() {
		try {
			oos.writeObject("SCREEN_CAPTURE");
			oos.flush();
		} catch (Exception e) {
			// TODO: handle exception
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
