package Application;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import Function.ProcessClass;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import ShutDown.ShutDown;
import Tabs.KeyLoggerTab;
import Tabs.ProcessTab;
import Tabs.ScreenCaptureTab;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main_Window extends JFrame {

	private String host = "";
	private int port;
	private JPanel contentPane;
	private JPanel Header = new JPanel();
	private ClientConnection cc;
	private Main_Window self = this;
	
	private final JDesktopPane desktopPane = new JDesktopPane();

	public Main_Window(final String host, int port, ClientConnection cc) {
		this.port = port;
		this.host = host;
		this.cc = cc;
		
		this.cc.setMain_Window(self);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					System.out.println("1");
					cc.endConnection(false);
				} catch (IOException e1) {
					e1.getStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(new Color(51, 51, 51));
		setResizable(false);
		this.host = host;
		this.port = port;
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

//		ProcessThread processList = new ProcessThread(host, 8080, contentPane);

		// Panel
		Header.setBounds(0, 0, 786, 51);
		Header.setBackground(new Color(114, 110, 255));
		contentPane.add(Header);

		JPanel nav = new JPanel();
		nav.setBorder(null);
		nav.setBounds(0, 50, 155, 513);
		nav.setBackground(new Color(55, 70, 91));
		contentPane.add(nav);
		nav.setLayout(new GridLayout(10, 1, 0, 0));

		desktopPane.setBounds(154, 50, 632, 513);
		desktopPane.setLayout(new BorderLayout(0, 0));
		desktopPane.setDragMode(ABORT);
		contentPane.add(desktopPane);

		// Button
		JButton btnShutDown = new JButton("Shut Down");
		btnShutDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShutDown shutDown = new ShutDown(host, port);
				shutDown.sendRequest();
				String data = (String) shutDown.getResponseData();
				// Dung de thoat chuong trinh
			}
		});
		btnShutDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnShutDown.setBackground(new Color(112, 235, 237));
				btnShutDown.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnShutDown.setBackground(nav.getBackground());
				btnShutDown.setForeground(Color.white);
			}
		});
		btnShutDown.setFont(new Font("Arial", Font.PLAIN, 15));
		btnShutDown.setForeground(Color.WHITE);
		btnShutDown.setBackground(new Color(55, 70, 91));
		btnShutDown.setBorder(null);
		nav.add(btnShutDown);

		JButton btnScreenCapture = new JButton("Screen Capture");
		btnScreenCapture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenCaptureTab screeCaptureTab = new ScreenCaptureTab(host, port);
				desktopPane.removeAll();
				desktopPane.add(screeCaptureTab);
			}
		});
		btnScreenCapture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnScreenCapture.setBackground(new Color(112, 235, 237));
				btnScreenCapture.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnScreenCapture.setBackground(nav.getBackground());
				btnScreenCapture.setForeground(Color.white);
			}
		});
		btnScreenCapture.setFont(new Font("Arial", Font.PLAIN, 15));
		btnScreenCapture.setForeground(Color.WHITE);
		btnScreenCapture.setBackground(new Color(55, 70, 91));
		btnScreenCapture.setBorder(null);
		nav.add(btnScreenCapture);

		JButton btnKeyLogger = new JButton("Key Logger");
		btnKeyLogger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyLoggerTab keyLoggerTab = new KeyLoggerTab(host, port);
				desktopPane.removeAll();
				desktopPane.add(keyLoggerTab);

			}
		});
		btnKeyLogger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnKeyLogger.setBackground(new Color(112, 235, 237));
				btnKeyLogger.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnKeyLogger.setBackground(nav.getBackground());
				btnKeyLogger.setForeground(Color.white);
			}
		});
		btnKeyLogger.setFont(new Font("Arial", Font.PLAIN, 15));
		btnKeyLogger.setForeground(Color.WHITE);
		btnKeyLogger.setBorder(null);
		btnKeyLogger.setBackground(new Color(55, 70, 91));
		nav.add(btnKeyLogger);

		JButton btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessTab processTab = new ProcessTab(host, port);
				desktopPane.removeAll();
				desktopPane.add(processTab);
			}
		});
		btnProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProcess.setBackground(new Color(112, 235, 237));
				btnProcess.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnProcess.setBackground(nav.getBackground());
				btnProcess.setForeground(Color.white);
			}
		});

		btnProcess.setFont(new Font("Arial", Font.PLAIN, 15));
		btnProcess.setForeground(Color.WHITE);
		btnProcess.setBorder(null);
		btnProcess.setBackground(new Color(55, 70, 91));
		btnProcess.setSize(getPreferredSize().width, 100);
		nav.add(btnProcess);

//		contentPane.add(textPane);

	}

	public void returnToConnectionTab() {
		Client_GUI clApp = new Client_GUI();
		clApp.frame.setVisible(true);
		this.dispose();
	}
}
