package Function;

import java.net.*;

import javax.swing.*;

import java.io.*;

public class ProcessThread {
	  
    private JTextPane textPane = null;
    private JScrollPane scrollPane = null;
	private String host = null;
	private int port ;
	private JPanel contentPanel = null;
	public ProcessThread(String host,int port,JPanel contentPanel) {
		this.host = host;
		this.port = port;
		this.contentPanel = contentPanel;
	}
	public void run() {
		Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
		try {
			socket = new Socket(host, port);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            oos.writeObject("GET_PROCESS_LIST");
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            
            textPane = new JTextPane();
    		textPane.setBounds(180, 61, 584, 781);
    		
    		scrollPane = new JScrollPane();
    		scrollPane.setBounds(180, 214, 584, 291);
    		scrollPane.setViewportView(textPane);
    		
    		textPane.setText(message);
    		textPane.setEditable(false);
    		contentPanel.add(scrollPane);
            //close resources
            ois.close();
            oos.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	public void clear() {
		
			contentPanel.remove(scrollPane);
			contentPanel.remove(textPane);
		
	}
	
}
