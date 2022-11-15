package Function;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerThread extends Thread{
	private InputStream inputStream = null;
	private OutputStream outputStream = null;
	private Socket clientSocket = null;
	
	public WorkerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		try {
			inputStream = clientSocket.getInputStream();
			outputStream = clientSocket.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String request = "";
			while(in.ready()) {
				request +=  String.valueOf((char)in.read());
			}
			System.out.println(request);
			
			if(request.equals("GET_PROCESS_LIST")) {
				try {
					Process list = Runtime.getRuntime().exec("tasklist");
					BufferedReader processBuffer = new BufferedReader(new InputStreamReader(list.getInputStream()));
					String processLine = "";
					while((processLine = processBuffer.readLine()) != null) {
						System.out.println(processLine);
					}
					
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else if(request.equals("CLOSE_PROCESS")) {
				
			} 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
