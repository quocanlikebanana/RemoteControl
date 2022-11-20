package Function;

import java.net.*;

import ScreenCapture.ScreenCapture;
import process.ListProcess;
import process.ProcessKill;
import process.ProcessStart;

import java.io.*;

public class ClientThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	private Socket clientSocket = null;
	private String record = "";

	public ClientThread(Socket clientSocket) {
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

			if (request.equals("GET_PROCESS_LIST")) {
				
				
				
				ListProcess listProcess = new ListProcess(oos);
				listProcess.set_listProcess();
				listProcess.sendListProcess();
			}

			else if (request_elements[0].equals("KILL_PROCESS")) {
				
				
				
				ProcessKill processKill = new ProcessKill(oos, request_elements[1]);
				processKill.kill();
				processKill.send_result();
			}

			else if (request_elements[0].equals("START_PROCESS")) {
				
				
				
				ProcessStart processStart = new ProcessStart(oos, request_elements[1]);
				processStart.start_file();
				processStart.send_result();
			}

//			else if (request.equals("KEY_LOGGER_START")) {
//
//			} 
//			
//			else if (request.equals("KEY_LOGGER_STOP")) {
//
//			} 
			
			else if (request.equals("SCREEN_CAPTURE")) {
				
				System.out.println(request);
				
				ScreenCapture screenCapture = new ScreenCapture(oos);
				screenCapture.get_Screenshot();
				screenCapture.send_ScreenShot();
			}
			
			else {
				throw new Exception();
			}
			
			oos.close();
			ois.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
