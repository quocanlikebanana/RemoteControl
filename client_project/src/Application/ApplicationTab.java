package Application;


import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import App.ListApp;
import Process.ProcessKill;
import Process.ProcessStart;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import App.AppKill;
import App.AppStart;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class ApplicationTab extends JInternalFrame {
	private String host = "";
	private int port;
	private JTextField killPID;
	private JTextField startName;
	
	public ApplicationTab(String host,int port) {
		this.host = host;
		this.port = port;
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		setVisible(true);
		JScrollPane listAppPane = new JScrollPane();
		listAppPane.setBounds(10, 124, 580, 239);
		getContentPane().add(listAppPane);
		
		JTextPane textPane = new JTextPane();
		 
		
		
		JButton btnStartApplication = new JButton("START APPLICATION");
		btnStartApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String status = "Please innput Application Name...";
					if(!startName.getText().isEmpty()) {
						AppStart appStart = new AppStart(host, port);
						appStart.sendRequest(startName.getText());
						status = (String) appStart.getResponseData();
					}
					JOptionPane.showMessageDialog(null, status , "" , JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnStartApplication.setBounds(10, 74, 160, 21);
		getContentPane().add(btnStartApplication);
		
		JButton btnGetApplication = new JButton("GET APPLICATION");
		btnGetApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListApp listApplication = new ListApp(host, port);
					listApplication.sendRequest();
					String list = (String) listApplication.getResonseData();
					listAppPane.setViewportView(textPane);
					textPane.setText(list);
					textPane.setEditable(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGetApplication.setBounds(10, 12, 160, 21);
		getContentPane().add(btnGetApplication);
		
		JButton btnKillApplication = new JButton("KILL APPLICATION");
		btnKillApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String status = "Please input pID...";
					if(!killPID.getText().isEmpty()) {
						AppKill appKill = new AppKill(host, port);
						appKill.sendRequest(killPID.getText());
						status = (String) appKill.getResponseData();
					}
					JOptionPane.showMessageDialog(null, status , "" , JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e2) {
					e2.getStackTrace();
				}
				
			}
		});
		btnKillApplication.setBounds(10, 43, 160, 21);
		getContentPane().add(btnKillApplication);
		
		killPID = new JTextField();
		killPID.setBounds(342, 43, 208, 19);
		getContentPane().add(killPID);
		killPID.setColumns(10);
		
		startName = new JTextField();
		startName.setBounds(342, 74, 208, 19);
		getContentPane().add(startName);
		startName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Kill pID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(207, 41, 77, 21);
		getContentPane().add(lblNewLabel);
		
		JLabel lblStartName = new JLabel("Application Name:");
		lblStartName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartName.setBounds(207, 72, 114, 21);
		getContentPane().add(lblStartName);
		
	

	}
}
