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

	@Override
	public void run() {
		try {
			while (exit == false) {
				Thread.sleep(1000);
				if (cc.checkConnection() == false) {
					// End Connection passively
					cc.endConnection(true);
					
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("e");
		}
	}
}
