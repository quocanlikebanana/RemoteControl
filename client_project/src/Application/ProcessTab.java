package Application;


import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JFrame;

public class ProcessTab extends JInternalFrame {
	private String host = "";
	
	public ProcessTab(String host) {
		this.host = host;
		setBorder(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 64, 128));
		setBounds(100, 100, 450, 300);
		
		setVisible(true);

	}

}
