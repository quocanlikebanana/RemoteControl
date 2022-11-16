package Function;

import java.net.*;
import java.io.*;

public class WorkerThread extends Thread{
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
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			String request = (String) ois.readObject();
			if(request.equals("GET_PROCESS_LIST")) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(outputStream);
				String[] commandList = {"powershell.exe", "-Command", "Get-Process"};
				ProcessBuilder processBuilder = new ProcessBuilder(commandList);
				Process list = processBuilder.start();
				BufferedReader processBuffer = new BufferedReader(new InputStreamReader(list.getInputStream()));
				String processLine = "";
				String outData = "";
				while((processLine = processBuffer.readLine() )!= null) {
					outData += processLine + '\n';
				}
				System.out.println(outData);
				oos.writeObject(outData);
	            ois.close();
	            oos.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else if(request.equals("GET_APPLICATION_LIST")) {
			try {
				String[] commandList = {"powershell.exe", "-Command", "gps | ? {$_.mainwindowtitle} | select name, id, mainwindowtitle | ft -AutoSize"};
				ProcessBuilder processBuilder = new ProcessBuilder(commandList);
				Process list = processBuilder.start();
				BufferedReader processBuffer = new BufferedReader(new InputStreamReader(list.getInputStream()));
				String processLine = "";
				while((processLine = processBuffer.readLine()) != null) {
					System.out.println(processLine);
				}
				
			} catch (Exception e) {
				e.getStackTrace();
			}
		} 

			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
