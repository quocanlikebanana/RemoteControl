package Application;


import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Process.ListProcess;
import Process.ProcessKill;
import Process.ProcessStart;
import ScreenCapture.ScreenCapture;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;

public class ScreeCaptureTab extends JInternalFrame {
	private String host = "";
	private int port;
	private BufferedImage bImage2 = null;
	public ScreeCaptureTab(String host,int port) {
		this.host = host;
		this.port = port;
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		setVisible(true);
		
		
	
		JButton btnScreenCap = new JButton("Screnn Capture");
		btnScreenCap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ScreenCapture screenCapture = new ScreenCapture(host, port);
					screenCapture.sendReuqest();
					byte[]  data = (byte[]) screenCapture.getResponseData();
					System.out.println(data);
					ByteArrayInputStream bis = new ByteArrayInputStream(data);
				    bImage2 = ImageIO.read(bis);
					
				    
//				    ImageIcon image = new ImageIcon(bImage2);
				    
			
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		btnScreenCap.setBounds(50, 40, 139, 34);
		getContentPane().add(btnScreenCap);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(bImage2, "jpg", new File("output.jpg") );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(271, 40, 139, 34);
		getContentPane().add(btnNewButton_1);
		
		
		
	

	}
}
