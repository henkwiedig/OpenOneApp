package com.openoneapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JLabel;

public class OpenOneApp {

	private JFrame frame;
	private JButton btnConnect;
	
	
	private MIB mib;
	private JButton btnDisconnect;
	private JButton btnDiscover;
	private JLabel lblIp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenOneApp window = new OpenOneApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpenOneApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel setup_panel = new JPanel();
		JPanel data_panel = new JPanel();
		tabbedPane.addTab("Setup", null, setup_panel, null);
		tabbedPane.addTab("Data", null, data_panel, null);
		
		btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Connecting");
				 mib = new MIB();
			     Thread read = new Thread( new Reader(mib) );
				 read.start();
			}
		});
		
		btnDiscover = new JButton("Discover");
		btnDiscover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Discover");
				 ServiceBeaconListener sb = new ServiceBeaconListener();
				 System.out.println(sb.getBeacon());
				 lblIp.setText(sb.getBeacon().split("/")[3].split(":")[0]);
				 
			}
		});
		setup_panel.add(btnDiscover);
		setup_panel.add(btnConnect);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					mib.car.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		setup_panel.add(btnDisconnect);
		
		lblIp = new JLabel("Ip");
		setup_panel.add(lblIp);
	}

}
