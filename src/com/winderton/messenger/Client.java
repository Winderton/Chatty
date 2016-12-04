package com.winderton.messenger;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.winderton.messenger.net.Net;
 
 public class Client extends JFrame {
 	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
 
 	private String name, address;
 	private int port;
	private JTextField txtMessage;
	private JTextArea history;
 	private boolean connected = false;
 	private Net net = null;
 
 	
 	public Client(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		net = new Net(port);
		
		connected = net.openConnection(address);
		if (!connected) { 
			System.out.println("Connection failed.."); 
			console("Connection failed..");
		}
		createWindow();
		console("Пытаюсь подключиться к: " + address + " на " + port + " порт, пользователь: " + name );
 	}
 
 	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		setTitle("Messenger Client");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		setSize(880, 550);
 		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 		setContentPane(contentPane);
 		
 		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{16, 857, 7};
		gbl_contentPane.columnWidths = new int[]{16, 827, 30, 7};
 		gbl_contentPane.rowHeights = new int[]{35, 475, 40};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
 		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
 		contentPane.setLayout(gbl_contentPane);
 		
 		history = new JTextArea();
		history.setEditable(false);
		history.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane scroll = new JScrollPane(history);
 		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.insets = new Insets(0, 0, 5, 5);
 		scrollConstraints.fill = GridBagConstraints.BOTH;
 		scrollConstraints.gridx = 0;
 		scrollConstraints.gridy = 0;
		scrollConstraints.gridwidth = 3;
		scrollConstraints.gridheight = 2;
		scrollConstraints.insets = new Insets(0, 7, 0, 0);
 		contentPane.add(scroll, scrollConstraints);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					send(txtMessage.getText());
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(txtMessage.getText());
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		contentPane.add(btnSend, gbc_btnSend);
 		setVisible(true);
 		
 		txtMessage.requestFocusInWindow();
 	}
 	
 	public void send(String message) {
 		if (message.equals("")) return;
 		message = name + ": " + message;
		console(message);
		txtMessage.setText("");
 	}
 	
 	public void console(String message) {
 		history.setCaretPosition(history.getDocument().getLength());
 		history.append(message + "\n\r");
 	}
 	
 }