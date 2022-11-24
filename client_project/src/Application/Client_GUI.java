package Application;


import java.io.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Client_GUI {
	private JTextField inputIP;
	public static Client_GUI window = null;
	private JButton btnConnection;

	public JFrame frame;
	public String host;
	public final int port = 8080;
	private JPanel Right;
	private JLabel label;
	public class RoundButton extends JButton
	{
		
		
	      public RoundButton(String label)
	      {
	          super(label);
	          //Enlarge button to make a circle rather than an oval.
	          Dimension size = getPreferredSize();
	          size.width = size.height = Math.max(size.width, size.height);
	          setPreferredSize(size);
	          //This call causes the JButton *not* to paint the background
	          //and allows us to paint a round background instead.
	          setContentAreaFilled(false);
	      }

	      //Paint the round background and label.
	      protected void paintComponent(Graphics g)
	      {
	          if (getModel().isArmed())
	              //You might want to make the highlight color
	              //a property of the RoundButton class.
	              g.setColor(getForeground());
	          else
	              g.setColor(getBackground());
	          g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
//	          g.fillOval(0, 0, getSize().width-1, getSize().height-1);
	          //This call will paint label and focus rectangle.
	          super.paintComponent(g);
	      }

	      //Paint the border of the button using a simple stroke.
	      protected void paintBorder(Graphics g)
	      {
	          g.setColor(new Color(92, 90, 91));
	          g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	      }
	  }
	
	
	public class RoundJTextField extends JTextField {
	    private Shape shape;
	    public RoundJTextField(int size) {
	        super(size);
	        setOpaque(false); // As suggested by @AVD in comment.
	    }
	    protected void paintComponent(Graphics g) {
	         g.setColor(getBackground());
	         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	         super.paintComponent(g);
	    }
	    protected void paintBorder(Graphics g) {
	         g.setColor(getForeground());
	         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	    }
	    public boolean contains(int x, int y) {
	         if (shape == null || !shape.getBounds().equals(getBounds())) {
	             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	         }
	         return shape.contains(x, y);
	    }
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Client_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("e main client");
				}
			}
		});
	}

	public Client_GUI() {
		initialize();
	}

	private void initialize() {
		
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 817, 499);
		frame.setResizable(false);
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(null);
		ImageIcon logo = new ImageIcon("Images/tiger2.jpg");
		JLabel picLabel = new JLabel(new ImageIcon(logo.getImage().getScaledInstance(407,480,0)));	
		JPanel left = new JPanel();
		left.add(picLabel);
		left.setBackground(new Color(236, 244, 255));
		left.setBounds(0, -11, 407, 523);
		
		
		frame.getContentPane().add(left);
		
			
		Right = new JPanel();
		Right.setBackground(new Color(92, 90, 91));
//		Right.setLayout(null);
		
		Right.setBounds(407, 0, 423, 512);
		frame.getContentPane().add(Right);
		
       
		
		
				btnConnection = new RoundButton("CONNECT");
				btnConnection.setFocusPainted(false);
				btnConnection.setForeground(Color.black);
				btnConnection.setBackground(Color.GREEN);
				btnConnection.setBounds(51, 245, 302, 52);
				btnConnection.setBorder(null);
				btnConnection.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnConnection.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
								try {
									host = inputIP.getText();
									ClientConnection cc = new ClientConnection(host, port);
									if (cc.checkStartConnection() == true) {
										ClientApplication_GUI mainWindow = new ClientApplication_GUI(host, port,
												cc);
										mainWindow.setVisible(true);
										frame.dispose();
									} else {
										host = "";
										JOptionPane.showMessageDialog(null, "error", "InfoBox: ",
												JOptionPane.INFORMATION_MESSAGE);
									}
								} catch (Exception e1) {
									System.out.println("e btnConnection");
								}
							}
						});
		
		label = new JLabel("");
		label.setBounds(176, 19, 0, 0);
						
						

		inputIP = new JTextField("Server-IP");
		inputIP.setBounds(82, 142, 271, 52);
		inputIP.setBorder(null);
		inputIP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inputIP.setColumns(10);
		Right.setLayout(null);
		Right.add(btnConnection);
		Right.add(label);
		Right.add(inputIP);
		
		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(46, 142, 33, 52);
		Right.add(lblNewLabel);
		frame.setLocationRelativeTo(null);
		frame.setTitle("RemoteControl_Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
