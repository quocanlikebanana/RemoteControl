package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Client_Application {
	private JTextField inputIP;
	public  static Client_Application window = null;
	private JButton btnConnection;
	
	public JFrame frame;
	public String host;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Client_Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Client_Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.getContentPane().setLayout(null);
		
		inputIP = new JTextField();
		inputIP.setBounds(103, 92, 319, 40);
		frame.getContentPane().add(inputIP);
		inputIP.setColumns(10);
		
		btnConnection = new JButton("Connect");
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					host = inputIP.getText();
					Socket checkConnection= new Socket(host,8080);
					if(checkConnection.isConnected()) {
						Main_Window mainWindow = new Main_Window(host);
						mainWindow.setVisible(true);
						frame.dispose();
					}
					checkConnection.close();					
				} catch (Exception e2) {
					// TODO: handle exception
					host = "";
					JOptionPane.showMessageDialog(null, "error", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnConnection.setBounds(453, 85, 85, 52);
		frame.getContentPane().add(btnConnection);
		frame.setLocationRelativeTo(null);
		frame.setTitle("RemoteControl_Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
