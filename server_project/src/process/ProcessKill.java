package process;

import java.io.File;
import java.io.IOException;
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
		ListProcess lp = new ListProcess(this.oos);
		if (lp.listProcess.contains(this.pID)) {
			try {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "taskkill /PID " + this.pID);
				pb.directory(new File("C:"));
				pb.start();
				res = "Kill successfully!";
			} catch (Exception e) {
				// TODO: handle exception
				res = "Coundn't kill this process!";
			}
		} else {
			res = "Process not found!";
		}
	}

	public void send_result() throws IOException {
		oos.writeObject(this.res);
	}
}
