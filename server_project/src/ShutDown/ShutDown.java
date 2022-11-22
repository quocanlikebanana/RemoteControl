package ShutDown;

import java.io.ObjectOutputStream;

public class ShutDown {
	private ObjectOutputStream oos = null;

	public ShutDown(ObjectOutputStream oos) {
		this.oos = oos;
	}

	public void shutDown() {

		try {
			oos.writeObject("END");
			oos.flush();
			
			// Confirm..?
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("shutdown -s -t 0");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
