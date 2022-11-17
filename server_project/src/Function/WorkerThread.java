package Function;

import java.net.*;

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
				ListProcess listProcess = new ListProcess(oos);
				listProcess.set_listProcess();
				listProcess.sendListProcess();
			} else if (request.equals("KILL_PROCESS")) {
				ProcessKill processKill = new ProcessKill(oos, request_elements[1]);
				processKill.kill();
				processKill.send_result();
			} else if (request.equals("START_PROCESS")) {
				ProcessStart processStart = new ProcessStart(oos, request_elements[1]);
				processStart.start_file();
				processStart.send_result();
			}else {
				// ......... them code vao day...........//
				// last modyfied by phuxuan2k3 trainhaquedidepleuongcafe
			}
			oos.close();
			ois.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
