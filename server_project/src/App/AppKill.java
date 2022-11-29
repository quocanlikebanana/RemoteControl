package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class AppKill {
	public ObjectOutputStream oos;
	public String pID;
	public String res = "";

	public AppKill(ObjectOutputStream oos, String pID) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
		this.pID = pID;
	}

	public boolean kill() throws IOException {
		ListApp listApplication = new ListApp(oos);
		listApplication.setListApp();
		if (listApplication.listApp.contains(this.pID)) {
			String commandLine = "taskkill /PID " + this.pID;
			Process p = Runtime.getRuntime().exec(commandLine);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			this.res = input.readLine();
			return true;
		} else {
			this.res = "Wrong pID!";
			return false;
		}
	}

	public void send_result() throws IOException {
		oos.writeObject(this.res);
		oos.flush();
	}
}
