package Function;

import java.net.*;

import Application.Server_GUI;
import ScreenCapture.ScreenCapture;
import process.ListProcess;
import process.ProcessKill;
import process.ProcessStart;

import java.io.*;

public class ClientThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	private String fromIP;
	private String record;
	private Server_GUI main;

	public ClientThread(Socket clientSocket, Server_GUI main) {
		try {
			this.inputStream = clientSocket.getInputStream();
			this.outputStream = clientSocket.getOutputStream();
			this.fromIP = clientSocket.getInetAddress().toString().substring(1);
			this.main = main;
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

				this.record = "Get list of process";
				main.actionRecorded(fromIP, record);
			}

			else if (request_elements[0].equals("KILL_PROCESS")) {
				ProcessKill processKill = new ProcessKill(oos, request_elements[1]);
				boolean res = processKill.kill();
				processKill.send_result();

				this.record = "Kill process: ";
				if (res == true) {
					this.record += request_elements[1];
				} else {
					this.record += "pID not found";
				}
				main.actionRecorded(fromIP, record);
			}

			else if (request_elements[0].equals("START_PROCESS")) {

				ProcessStart processStart = new ProcessStart(oos, request_elements[1]);
				boolean res = processStart.start_file();
				processStart.send_result();

				this.record = "Start process: ";
				if (res == true) {
					this.record += request_elements[1];
				} else {
					this.record += "name not found";
				}
				main.actionRecorded(fromIP, record);
			}

//			else if (request.equals("KEY_LOGGER_START")) {
//
//			} 
//			
//			else if (request.equals("KEY_LOGGER_STOP")) {
//
//			} 

			else if (request.equals("SCREEN_CAPTURE")) {
				ScreenCapture screenCapture = new ScreenCapture(oos);
				boolean res = screenCapture.get_Screenshot();
				screenCapture.send_ScreenShot();
				
				this.record = "Take screenshot";
				if (res == false) {
					this.record += " - failed";
				}
				main.actionRecorded(fromIP, record);
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