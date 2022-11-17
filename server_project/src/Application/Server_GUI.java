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
	private JButton openServer;
	private JTextField textField;

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
		frmServer.setBounds(100, 100, 597, 342);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		frmServer.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connected to:");
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD, 16));
		lblNewLabel.setBounds(170, 10, 131, 33);
		frmServer.getContentPane().add(lblNewLabel);
		
		JLabel lblActions = new JLabel("Actions:");
		lblActions.setFont(new Font("Cascadia Code", Font.BOLD, 16));
		lblActions.setBounds(64, 140, 96, 33);
		frmServer.getContentPane().add(lblActions);
		
		textField = new JTextField();
		textField.setText("None");
		textField.setFont(new Font("Cascadia Code", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(311, 10, 232, 33);
		frmServer.getContentPane().add(textField);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 150, 105);
		frmServer.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(SystemColor.inactiveCaption);
		
		JButton openServer_1 = new JButton("Open Server");
		openServer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		openServer_1.setForeground(Color.WHITE);
		openServer_1.setFont(new Font("Arial", Font.BOLD, 16));
		openServer_1.setBorderPainted(false);
		openServer_1.setBorder(null);
		openServer_1.setBackground(new Color(0, 102, 51));
		openServer_1.setBounds(10, 10, 130, 40);
		panel.add(openServer_1);
		
		JButton closeServer = new JButton("Close Server");
		closeServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		closeServer.setForeground(Color.WHITE);
		closeServer.setFont(new Font("Arial", Font.BOLD, 16));
		closeServer.setBorderPainted(false);
		closeServer.setBorder(null);
		closeServer.setBackground(new Color(153, 0, 0));
		closeServer.setBounds(10, 55, 130, 40);
		panel.add(closeServer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 125, 579, 5);
		frmServer.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(55,70,91));
		
		JList list = new JList();
		list.setBounds(170, 140, 373, 139);
		frmServer.getContentPane().add(list);
	}
}
