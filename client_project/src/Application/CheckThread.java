package Application;

public class CheckThread extends Thread {
	private ClientConnection cc;
	private volatile boolean exit = false;

	public CheckThread(ClientConnection cc) {
		this.cc = cc;
	}

	public void stopCheckThread() {
		this.exit = true;
	}

	public boolean isRunning() {
		return !this.exit;
	}

	@Override
	public void run() {
		try {
			while (exit == false) {
				Thread.sleep(1000);
				if (cc.checkConnection() == false) {
					break;
				}
			}
			///////////////////////////////
			if (exit == false) {
				// End Connection passively
				cc.endConnection(true);
			}
		} catch (Exception e) {
			System.out.println("e CheckThread");
		}
	}
}
