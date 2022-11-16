package Function;

import java.net.*;
import java.io.*;

public class WorkerThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	private Socket clientSocket = null;

	public WorkerThread(Socket clientSocket) {
		try {
			this.clientSocket = clientSocket;
			this.inputStream = clientSocket.getInputStream();
			this.outputStream = clientSocket.getOutputStream();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void run() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			String request = (String) ois.readObject();
			if(request.equals("GET_PROCESS_LIST")) {
				ListProcess listProcess = new ListProcess(oos);
				listProcess.set_listProcess();
				listProcess.sendListProcess();
			}

			oos.close();
			ois.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
