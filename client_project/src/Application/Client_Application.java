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
	public final int port = 8080;

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
	
	public Client_Application() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.getContentPane().setLayout(null);
		
		inputIP = new JTextField();
		inputIP.setBounds(103, 92, 319, 40);
		frame.getContentPane().add(inputIP);
		inputIP.setColumns(10);
		
		btnConnection = new JButton("Connect");
		btnConnection.setBounds(453, 85, 85, 52);
		frame.getContentPane().add(btnConnection);
		frame.setLocationRelativeTo(null);
		frame.setTitle("RemoteControl_Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					host = inputIP.getText();
					ClientConnection start = new ClientConnection(host, port);
					if (start.startConnection()) {
						Main_Window mainWindow = new Main_Window(host,port);
						mainWindow.setVisible(true);
						frame.dispose();
					}
				} catch (Exception e2) {
					// TODO: handle exception
					host = "";
					JOptionPane.showMessageDialog(null, "error", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
}
