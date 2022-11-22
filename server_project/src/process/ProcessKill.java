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

	public boolean kill() throws IOException {

		ListProcess listProcess = new ListProcess(oos);
		listProcess.set_listProcess();

		boolean is_exist = listProcess.listProcess.contains(this.pID);
		if (is_exist == true) {
			String commandLine = "taskkill /PID " + this.pID + " /F /T";
			Process p = Runtime.getRuntime().exec(commandLine);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String res_line = "";
			while ((res_line = input.readLine()) != null) {
				this.res += res_line;
			}
			return true;
		} else if (is_exist == false) {
			this.res = "Process not found!";
		} else if (this.res == null || this.res.length() == 0) {
			this.res = "The process could not be killed!";
		}
		return false;
		// xu ly th xoa file he thong khong duoc return false
	}

	public void send_result() throws IOException {
		oos.writeObject(this.res);
		oos.flush();
	}
}
