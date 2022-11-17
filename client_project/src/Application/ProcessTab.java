package Application;


import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Process.ListProcess;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class ProcessTab extends JInternalFrame {
	private String host = "";
	private int port;
	
	public ProcessTab(String host,int port) {
		this.host = host;
		this.port = port;
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		setVisible(true);
		JScrollPane listProcessPane = new JScrollPane();
		listProcessPane.setBounds(10, 124, 580, 239);
		getContentPane().add(listProcessPane);
		
		JTextPane textPane = new JTextPane();
		 
		
		
		JButton btnStartProcess = new JButton("START PROCESS");
		btnStartProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStartProcess.setBounds(10, 74, 160, 21);
		getContentPane().add(btnStartProcess);
		
		JButton btnGetProcess = new JButton("GET PROCESS");
		btnGetProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListProcess listProcess = new ListProcess(host, port);
					listProcess.sendRequest();
					String list = (String) listProcess.getResonseData();
					listProcessPane.setViewportView(textPane);
					textPane.setText(list);
					textPane.setEditable(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGetProcess.setBounds(10, 12, 160, 21);
		getContentPane().add(btnGetProcess);
		
		JButton btnKillProcess = new JButton("KILL PROCESS");
		btnKillProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnKillProcess.setBounds(10, 43, 160, 21);
		getContentPane().add(btnKillProcess);
		
	

	}
}
