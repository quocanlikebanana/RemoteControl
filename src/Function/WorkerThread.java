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
			if (request.equals("GET_PROCESS_LIST")) {
				try {
					ListProcess listProcess = new ListProcess(oos);
					listProcess.set_listProcess();
					listProcess.sendListProcess();
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else if (request.equals("GET_APPLICATION_LIST")) {
				try {
					String[] commandList = { "powershell.exe", "-Command",
							"gps | ? {$_.mainwindowtitle} | select name, id, mainwindowtitle | ft -AutoSize" };
					ProcessBuilder processBuilder = new ProcessBuilder(commandList);
					Process list = processBuilder.start();
					BufferedReader processBuffer = new BufferedReader(new InputStreamReader(list.getInputStream()));
					String processLine = "";
					while ((processLine = processBuffer.readLine()) != null) {
						System.out.println(processLine);
					}

				} catch (Exception e) {
					e.getStackTrace();
				}
			} else if (request.equals("START_PROCESS")) {

			}
			oos.close();
			ois.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
