package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.table.DefaultTableModel;

import Function.ConnectionThread;

import java.awt.SystemColor;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class Server_GUI {
	private JFrame frmServer;
	private JButton openBtn;
	private JButton closeBtn;
	private ConnectionThread con;
	private JTable actTable;
	private JList<String> ipList;
	private DefaultListModel<String> ipListModel;
	private DefaultTableModel actModel;
	private JScrollPane actionTableScrollPane;

	public Server_GUI self = this;

	public Server_GUI getSelf() {
		return this;
	}

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
		postInitializeSettings();

		/* Testing Area */
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
		frmServer.setBounds(100, 100, 544, 416);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		frmServer.getContentPane().setLayout(null);

		openBtn = new JButton("Open Server");
		openBtn.setBounds(10, 10, 130, 40);
		frmServer.getContentPane().add(openBtn);
		openBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		openBtn.addActionListener(new ActionListener() {
			// OPEN Connection
			public void actionPerformed(ActionEvent e) {
				con = new ConnectionThread(self);
				con.start();

				openBtn.setVisible(false);
				openBtn.setEnabled(false);
				closeBtn.setVisible(true);
				closeBtn.setEnabled(true);
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
			// CLOSE all Connection 
			public void actionPerformed(ActionEvent e) {
				openBtn.setVisible(true);
				openBtn.setEnabled(true);
				closeBtn.setVisible(false);
				closeBtn.setEnabled(false);

				con.stopConnection();
			}
		});
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
		closeBtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		closeBtn.setBackground(new Color(153, 0, 0));

		JLabel lblNewLabel = new JLabel("Clients:");
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD, 14));
		lblNewLabel.setBounds(163, 14, 120, 33);
		frmServer.getContentPane().add(lblNewLabel);

		JLabel lblActions = new JLabel("Actions:");
		lblActions.setFont(new Font("Arial", Font.BOLD, 14));
		lblActions.setBounds(10, 155, 73, 33);
		frmServer.getContentPane().add(lblActions);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 129, 527, 5);
		frmServer.getContentPane().add(panel_1);
		panel_1.setBackground(new Color(55, 70, 91));

		JScrollPane listIpScrollPane = new JScrollPane();
		listIpScrollPane.setPreferredSize(new Dimension(250, 80));
		listIpScrollPane.setBounds(293, 10, 190, 95);
		frmServer.getContentPane().add(listIpScrollPane);

		ipList = new JList<String>();
		ipList.setVisibleRowCount(4);
		ipList.setSelectionBackground(SystemColor.activeCaptionBorder);
		listIpScrollPane.setViewportView(ipList);

		actionTableScrollPane = new JScrollPane();
		actionTableScrollPane.setBounds(20, 189, 463, 146);
		frmServer.getContentPane().add(actionTableScrollPane);

		actTable = new JTable();
		actionTableScrollPane.setViewportView(actTable);
		actTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	}

	private void postInitializeSettings() {

		// Remove blue line wrap the text inside buttons
		openBtn.setFocusPainted(false);
		closeBtn.setFocusPainted(false);

		// By default
		closeBtn.setEnabled(false);

		ipListModel = new DefaultListModel<String>();
		ipList.setModel(ipListModel);

		actModel = new DefaultTableModel(new Object[][] {}, new String[] { "IP", "Action" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				actTable.setFocusable(false);
				return false;
			}
		};

		actTable.setModel(actModel);
		actTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		actTable.getColumnModel().getColumn(1).setMinWidth(250);
		actTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	public void addToIpList(String ip) {
		this.ipListModel.addElement(ip);
	}

	public void removeFromIpList(String ip) {
		int id = this.ipListModel.indexOf(ip);
		if (id == -1)
			return;
		this.ipListModel.remove(id);
	}

	public void actionRecorded(String ip, String act) {
		this.actModel.addRow(new Object[] { ip, act });
		this.actTable.repaint();
	}

}
