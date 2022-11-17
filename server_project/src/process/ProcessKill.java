package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class ProcessKill {
	public ObjectOutputStream oos;
	public String pID;
	public String res = "";

	public ProcessKill(ObjectOutputStream oos, String pID) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
		this.pID = pID;
	}

	public void kill() throws IOException {
		String commandLine = "taskkill /PID " + this.pID;
	    Process p = Runtime.getRuntime().exec(commandLine);
	    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    this.res = input.readLine();
	    if(this.res.isEmpty()) {
	    	this.res = "The system cannot find the path specified.";
	    }
	}

	public void send_result() throws IOException {
		oos.writeObject(this.res);
		oos.flush();
	}
}
