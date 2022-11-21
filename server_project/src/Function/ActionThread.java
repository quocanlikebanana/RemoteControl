package Function;

import java.net.*;

import App.AppKill;
import App.AppStart;
import App.ListApp;
import Application.Server_GUI;
import KeyLogger.KeyLogger;
import ScreenCapture.ScreenCapture;
import ShutDown.ShutDown;
import process.ListProcess;
import process.ProcessKill;
import process.ProcessStart;

import java.io.*;

public class ActionThread extends Thread {
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	private String fromIP;
	private String record;
	private Server_GUI main;

	public ActionThread(Socket clientSocket, Server_GUI main) {
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
			ObjectInputStream ois = new ObjectInputStream(inputStream); // throws in first run, need to anylize what
																		// happened in first run

			String request = (String) ois.readObject();

			System.out.println(request);

			String[] request_elements = request.split(" ");

			if (request.equals("GET_PROCESS_LIST")) {
				ListProcess listProcess = new ListProcess(oos);
				listProcess.set_listProcess();
				listProcess.sendListProcess();

				this.record = "Get list of process";
				main.actionRecorded(fromIP, record);
			} else if (request.equals("GET_APPLICATION_LIST")) {
				ListApp listApplication = new ListApp(oos);
				listApplication.setListApp();
				listApplication.sendListProcess();

				this.record = "Get list of application";
				main.actionRecorded(fromIP, record);
			} else if (request_elements[0].equals("KILL_PROCESS")) {
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
			} else if (request_elements[0].equals("KILL_APPLICATION")) {
				AppKill appKill = new AppKill(oos, request_elements[1]);
				boolean res = appKill.kill();
				appKill.send_result();

				this.record = "Kill application: ";
				if (res == true) {
					this.record += request_elements[1];
				} else {
					this.record += "pID not found";
				}
				main.actionRecorded(fromIP, record);
			} else if (request_elements[0].equals("START_PROCESS")) {

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
			} else if (request_elements[0].equals("START_APPLICATION")) {

				AppStart appStart = new AppStart(oos, request_elements[1]);
				boolean res = appStart.start_file();
				appStart.send_result();

				this.record = "Start application: ";
				if (res == true) {
					this.record += request_elements[1];
				} else {
					this.record += "name not found";
				}
				main.actionRecorded(fromIP, record);
			}

			else if (request.equals("KEY_LOGGER_START")) {
				KeyLogger keyLogger = new KeyLogger(oos, "");
				keyLogger.get_Keylogger(oos);
			}

			else if (request.equals("KEY_LOGGER_STOP")) {
				KeyLogger keyLogger = new KeyLogger(oos, "");
				keyLogger.send_KeyLogger();
			} else if (request.equals("SHUT_DOWN")) {
				ShutDown shutDown = new ShutDown(oos);
				shutDown.shutDown();
			}

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

			System.out.println("2");

			oos.close();
			ois.close();
		} catch (StreamCorruptedException sce) {
			System.out.println("sce");
		} catch (IOException ioe) {
			// Catch on first run, need to check!
			main.addToIpList(fromIP);
		} catch (SecurityException se) {
			System.out.println("se");
		} catch (NullPointerException npe) {
			System.out.println("npe");
		} catch (Exception e) {
			System.out.println("e");
		}

	}
}
