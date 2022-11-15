package Application;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Function.Connection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main_Window extends JFrame {

	private String host = "";
	private JPanel contentPane;
	private JPanel Header = new JPanel();
		
	public Main_Window(final String host) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(new Color(51,51,51));
		setResizable(false);
		this.host = host;
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		//Panel
		
		Header.setBounds(0, 0, 786, 51);
		Header.setBackground(new Color(114,110,255));
		contentPane.add(Header);
		
		JPanel nav = new JPanel();
		nav.setBorder(null);
		nav.setBounds(0, 50, 155, 513);
		nav.setBackground(new Color(55,70,91));
		contentPane.add(nav);
		nav.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel show = new JPanel();
		show.setBounds(153, 50, 633, 513);
		contentPane.add(show);
		show.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		
		
		
		//Button		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setBackground(new Color(112, 235, 237));
				btnNewButton_1.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setBackground(nav.getBackground());
				btnNewButton_1.setForeground(Color.white);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(55,70,91));
		btnNewButton_1.setBorder(null);
		nav.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(new Color(112, 235, 237));
				btnNewButton.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(nav.getBackground());
				btnNewButton.setForeground(Color.white);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(55,70,91));
		btnNewButton.setBorder(null);
		nav.add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(new Color(112, 235, 237));
				button.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(nav.getBackground());
				button.setForeground(Color.white);
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 15));
		button.setForeground(Color.WHITE);
		button.setBorder(null);
		button.setBackground(new Color(55,70,91));
		nav.add(button);
		
		JButton btnProcess = new JButton("Get Process");
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
		btnProcess.setBackground(new Color(55,70,91));
		btnProcess.setSize(getPreferredSize().width,100);
		nav.add(btnProcess);
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = new Connection(host, 8080);
				connection.sendRequest("GET_PROCESS_LIST");
			}
		});
		
		
	}
}
