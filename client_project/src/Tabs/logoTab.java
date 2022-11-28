package Tabs;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class logoTab extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public logoTab() {
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		
		ImageIcon logo = new ImageIcon("Images/art.png");
		JLabel picLabel = new JLabel(new ImageIcon(logo.getImage().getScaledInstance(600, 373, DO_NOTHING_ON_CLOSE)));
		JPanel panel = new JPanel();
		panel.add(picLabel);
		panel.setBounds(0, 0, 600, 373);
		getContentPane().add(panel);
		setVisible(true);


	}
}
