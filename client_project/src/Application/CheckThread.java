package Application;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class CheckThread extends Thread {
	private ClientConnection cc;
	private volatile boolean exit = false;

	public CheckThread(ClientConnection cc) {
		this.cc = cc;
	}

	public void stopClientProcess() {
		this.exit = true;
	}

	@Override
	public void run() {
		try {
			while (exit == false) {
				if (cc.checkConnection() == false) {
					// Connection loss passive
					
					break;
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
