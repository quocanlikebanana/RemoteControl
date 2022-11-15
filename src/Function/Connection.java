package Function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import Application.Client_Application;

public class Connection {
	private String host = null;
	private int port;
    public Socket serverSocket = null;
    
    
    public  Connection(String host,int port) {
    	this.host = host;
    	this.port = port;
    	try {
			this.serverSocket = new Socket(this.host,this.port);
		} catch (Exception e) {
			e.getStackTrace();
		}
    }
	
    public void sendRequest(String request) {
    	try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
			out.write(request);
			out.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void getResponse(String response) {
    	try {
			BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    

}