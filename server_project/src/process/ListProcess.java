package process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class ListProcess {
	public ObjectOutputStream oos;
	public String listProcess = "";

	public ListProcess(ObjectOutputStream oos) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
	}

	public void set_listProcess() throws IOException {
		String[] commandList = { "powershell.exe", "-Command",
				"Get-Process | Select-Object Name, ID, @{Name='ThreadCount';Expression ={$_.Threads.Count}}" };
		ProcessBuilder processBuilder = new ProcessBuilder(commandList);
		Process list = processBuilder.start();
		BufferedReader processBuffer = new BufferedReader(new InputStreamReader(list.getInputStream()));
		String processLine = "";
		while ((processLine = processBuffer.readLine()) != null) {
			this.listProcess += processLine + '\n';
		}
	}

	public void sendListProcess() throws IOException {
		oos.writeObject(this.listProcess);
		oos.flush();
	}
}
