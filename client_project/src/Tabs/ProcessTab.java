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

public class ProcessTab extends JInternalFrame {
	private String host = "";
	private int port;
	private JTextField killPID;
	private JTextField startName;

	public ProcessTab(String host, int port) {
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
				try {
					String status = "Please innput Process Name...";
					if (!startName.getText().isEmpty()) {
						ProcessStart processStart = new ProcessStart(host, port);
						processStart.sendRequest(startName.getText());
						status = (String) processStart.getResponseData();
					}
					JOptionPane.showMessageDialog(null, status, "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e2) {
					// TODO: handle exception
				}
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
				try {
					String status = "Please input pID...";
					if (!killPID.getText().isEmpty()) {
						ProcessKill processKill = new ProcessKill(host, port);
						processKill.sendRequest(killPID.getText());
						status = (String) processKill.getResponseData();
					}
					JOptionPane.showMessageDialog(null, status, "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e2) {
					e2.getStackTrace();
				}

			}
		});
		btnKillProcess.setBounds(10, 43, 160, 21);
		getContentPane().add(btnKillProcess);

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

		JLabel lblStartName = new JLabel("Process Name:");
		lblStartName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStartName.setBounds(207, 72, 114, 21);
		getContentPane().add(lblStartName);

	}
}
