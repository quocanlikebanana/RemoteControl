package Application;


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
				Thread.sleep(1000);
				if (cc.checkConnection() == false) {
					// End Connection passive
					System.out.println("5");
					cc.endConnection(true);
					
					break;
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
