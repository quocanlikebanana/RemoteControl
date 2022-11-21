package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class ListApp {
	public ObjectOutputStream oos;
	public String listApp = "";

	public ListApp(ObjectOutputStream oos) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
	}

	public void setListApp() throws IOException {
		String[] commandList = { "powershell.exe", "-Command",
				"get-process | where-object {$_.mainwindowhandle -ne 0} | select-object name, id" };
		ProcessBuilder processBuilder = new ProcessBuilder(commandList);
		Process list = processBuilder.start();
		BufferedReader processBuffer = new BufferedReader(new InputStreamReader(list.getInputStream()));
		String processLine = "";
		while ((processLine = processBuffer.readLine()) != null) {
			this.listApp += processLine + '\n';
		}
	}

	public void sendListProcess() throws IOException {
		oos.writeObject(this.listApp);
	}
}
