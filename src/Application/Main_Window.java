package Application;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Function.Connection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main_Window extends JFrame {

	private String host = "";
	private JPanel contentPane;
		
	public Main_Window(final String host) {
		this.host = host;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		

		setContentPane(contentPane);
		
		JButton btnProcess = new JButton("Get process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = new Connection(host, 8080);
				connection.sendRequest("GET_PROCESS_LIST");
			}
		});
		btnProcess.setBounds(36, 124, 148, 61);
		contentPane.add(btnProcess);
	}
}
