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
			System.out.println("e ActionThread");
		}

	}

	@Override
	public void run() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			ObjectInputStream ois = new ObjectInputStream(inputStream); 
			KeyLogger KL = new KeyLogger(oos);
			String request = (String) ois.readObject();
			String[] request_elements = request.split(" ");

			if (request.equals("START")) {
				main.addToIpList(fromIP);
			} else if (request.equals("GET_PROCESS_LIST")) {
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
				main.actionRecorded(fromIP, record);}
			else if (request.equals("KEY_LOGGER_START")) {
//				System.out.println(recorded);
			//  if(recorded == false) {
			//	System.out.println("go in here");
			//	recorded = true;
			//	System.out.println(recorded);

				KL.get_Keylogger(oos);
				this.record = "Start key logging";
				main.actionRecorded(fromIP, record);
			}else if (request.equals("KEY_LOGGER_STOP")) {
				//KeyLogger KL = new KeyLogger(oos);
				//KL.unhook();
				KL.send_Keylogger();
				this.record = "End key logging";
				main.actionRecorded(fromIP, record);
			}else if (request.equals("DELETE_KEY")) {
				//KeyLogger KL = new KeyLogger(oos);
				KL.delete();
			//	KL.unhook();
			
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

			} else if (request.equals("SHUT_DOWN")) {
				ShutDown shutDown = new ShutDown(oos);
				shutDown.shutDown();
			} else if (request.equals("SCREEN_CAPTURE")) {
				ScreenCapture screenCapture = new ScreenCapture(oos);
				boolean res = screenCapture.get_Screenshot();
				screenCapture.send_ScreenShot();

				this.record = "Take screenshot";
				if (res == false) {
					this.record += " - failed";
				}
				main.actionRecorded(fromIP, record);
			} else if (request.equals("END")) {
				main.removeFromIpList(fromIP);
			} else if (request.equals("CHECK")) {
				// Do nothing
			}
			oos.close();
			ois.close();
		} catch (StreamCorruptedException sce) {
			System.out.println("sce run ActionThread");
		} catch (IOException ioe) {
			System.out.println("ioe run ActionThread");
		} catch (SecurityException se) {
			System.out.println("se run ActionThread");
		} catch (NullPointerException npe) {
			System.out.println("npe run ActionThread");
		} catch (Exception e) {
			System.out.println("e run ActionThread");
		}

	}
}
