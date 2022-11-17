package Application;


import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Process.ListProcess;
import Process.ProcessKill;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class ProcessTab extends JInternalFrame {
	private String host = "";
	private int port;
	private JTextField killPID;
	private JTextField startPID;
	
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
				ProcessKill processKill;
				try {
					processKill = new ProcessKill(host, port);
					processKill.sendRequest(killPID.getText());
					String status = (String) processKill.getResponseData();
					JOptionPane.showMessageDialog(null, status , "" , JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnKillProcess.setBounds(10, 43, 160, 21);
		getContentPane().add(btnKillProcess);
		
		killPID = new JTextField();
		killPID.setBounds(294, 43, 208, 19);
		getContentPane().add(killPID);
		killPID.setColumns(10);
		
		startPID = new JTextField();
		startPID.setBounds(294, 74, 208, 19);
		getContentPane().add(startPID);
		startPID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kill pID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(207, 41, 77, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel lblStartPid = new JLabel("Start pID:");
		lblStartPid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartPid.setBounds(207, 72, 77, 21);
		getContentPane().add(lblStartPid);
		
	

	}
}