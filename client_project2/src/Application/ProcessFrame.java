package Application;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JRootPane;

import java.awt.Color;
import javax.swing.JFrame;

public class ProcessFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ProcessFrame() {
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 64, 128));
		setBounds(100, 100, 450, 300);
		
		setVisible(true);

	}

}
