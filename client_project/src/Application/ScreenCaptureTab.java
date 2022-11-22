package Application;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import App.ListApp;
import Process.ProcessKill;
import Process.ProcessStart;
import ScreenCapture.ScreenCapture;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

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

public class ScreenCaptureTab extends JInternalFrame {
	private String host = "";
	private int port;
	private BufferedImage bImage2 = null;
	public String path;
	public ScreenCaptureTab(String host, int port) {
		this.host = host;
		this.port = port;
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		setVisible(true);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(35, 94, 527, 254);
//		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Admin\\Desktop\\GIT_DOAN\\dev\\RemoteControl\\client_project\\output.jpg"));
		getContentPane().add(lblNewLabel);

		JButton btnScreenCap = new JButton("Screen Capture");
		btnScreenCap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ScreenCapture screenCapture = new ScreenCapture(host, port);
					screenCapture.sendReuqest();
					byte[] data = (byte[]) screenCapture.getResponseData();
					System.out.println(data);
					ByteArrayInputStream bis = new ByteArrayInputStream(data);
					bImage2 = ImageIO.read(bis);
					lblNewLabel.setIcon(new ImageIcon(bImage2.getScaledInstance(527, 254, 100)));
					ImageIO.write(bImage2, "png", new File("output.png"));

				} catch (Exception e2) {
					// TODO: handle exception
					e2.getStackTrace();
				}

			}
		});

		btnScreenCap.setBounds(50, 40, 139, 34);
		getContentPane().add(btnScreenCap);

		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					JFrame parentFrame = new JFrame();
					 
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");   
					 
					int userSelection = fileChooser.showSaveDialog(parentFrame);
					 
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = fileChooser.getSelectedFile();
					   path = fileToSave.getAbsolutePath()+".png";	
					    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					    try {
							ImageIO.write(bImage2, "png",new File(path) );
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}
		});

		btnDownload.setBounds(271, 40, 139, 34);
		getContentPane().add(btnDownload);

	}
}
