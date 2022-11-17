package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class ProcessStart {
	public ObjectOutputStream oos;
	public String file_name;
	public boolean is_exist = false;
	public String file_path;
	public String res = "";

	public ProcessStart(ObjectOutputStream oos, String file_name) throws IOException {
		// TODO Auto-generated constructor stub
		this.oos = oos;
		this.file_name = file_name;

		set_file_path();
	}

	public void set_file_path() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir \"\\" + this.file_name + "\"" + " /s");
		pb.directory(new File("C:"));
		Process process = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String response_line;
		while ((response_line = br.readLine()) != null) {
			if (response_line.startsWith(" Directory") == true) {
				this.is_exist = true;
				String[] token = response_line.split(" ", 4);
				this.file_path = token[3];
				break;
			}
		}
		br.close();
	}

	public void start_file() throws IOException {
		if (this.is_exist == true) {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", this.file_name);
			pb.directory(new File(this.file_path));
			pb.start();
			this.res = "Start Process Successfully!";
		} else {
			this.res = "The file is not exist!";
		}
	}

	public void send_result() throws IOException {
		oos.writeObject(this.res);
	}
}
