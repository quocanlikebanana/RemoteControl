package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server_GUI {

	private JFrame frmServer;
	private JTextField ipTxF;
	private JButton openBtn;
	private JButton closeBtn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_GUI window = new Server_GUI();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server_GUI() {
		initialize();
		AnNgoAddOns();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmServer.setTitle("Server");
		frmServer.setAlwaysOnTop(true);
		frmServer.setResizable(false);
		frmServer.setBounds(100, 100, 542, 238);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		frmServer.getContentPane().setLayout(null);
		
		openBtn = new JButton("Open Server");
		openBtn.setBounds(10, 10, 130, 40);
		frmServer.getContentPane().add(openBtn);
		openBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		openBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBtn.setVisible(false);
				openBtn.setEnabled(false);
				closeBtn.setVisible(true);
				closeBtn.setEnabled(true);
				ipTxF.setText("Anybody there...?");
			}
		});
		openBtn.setForeground(Color.WHITE);
		openBtn.setFont(new Font("Arial", Font.BOLD, 14));
		openBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		openBtn.setBackground(new Color(0, 102, 51));
		
		closeBtn = new JButton("Close Server");
		closeBtn.setBounds(10, 10, 130, 40);
		frmServer.getContentPane().add(closeBtn);
		closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openBtn.setVisible(true);
				openBtn.setEnabled(true);
				closeBtn.setVisible(false);
				closeBtn.setEnabled(false);
				ipTxF.setText("None");
			}
		});
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
		closeBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		closeBtn.setBackground(new Color(153, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Connected to:");
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD, 14));
		lblNewLabel.setBounds(170, 10, 120, 33);
		frmServer.getContentPane().add(lblNewLabel);
		
		JLabel lblActions = new JLabel("Actions:");
		lblActions.setFont(new Font("Arial", Font.BOLD, 14));
		lblActions.setBounds(73, 75, 67, 33);
		frmServer.getContentPane().add(lblActions);
		
		ipTxF = new JTextField();
		ipTxF.setText("None");
		ipTxF.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		ipTxF.setEditable(false);
		ipTxF.setColumns(10);
		ipTxF.setBounds(300, 10, 192, 33);
		frmServer.getContentPane().add(ipTxF);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 524, 5);
		frmServer.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(55,70,91));
		
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 14));
		list.setBounds(170, 82, 322, 103);
		frmServer.getContentPane().add(list);
		
	}
	
	/* An Ngo's add-ons */
	private void AnNgoAddOns() {
		ipTxF.setText("None");
		
		// Remove blue line wrap the text inside buttons
		openBtn.setFocusPainted(false);
		closeBtn.setFocusPainted(false);
		
		// By default
		closeBtn.setEnabled(false);
	}
	
}
