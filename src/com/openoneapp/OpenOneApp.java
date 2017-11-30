package com.openoneapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.openoneapp.protocol.client.Abs;
import com.openoneapp.protocol.server.Match;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpenOneApp {
	
	private MIB mib;

	private JFrame frame;
	private JTextField txtIptext;
	private JTextField txtPorttext;
	private JPanel panel_1 ;
	
	private int count = 0;

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
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblIp = new JLabel("IP");
		panel.add(lblIp);
		
		txtIptext = new JTextField();
		txtIptext.setText("localhost");
		panel.add(txtIptext);
		txtIptext.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		panel.add(lblPort);
		
		txtPorttext = new JTextField();
		txtPorttext.setText("25010");
		panel.add(txtPorttext);
		txtPorttext.setColumns(10);
				
		JButton btnDiscover = new JButton("Discover");
		btnDiscover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ServiceBeaconListener sbl = new ServiceBeaconListener();
				String socket = sbl.getBeacon();
				txtIptext.setText(socket.split(":")[1].split("/")[2]);
				txtPorttext.setText(socket.split(":")[2]);
			}
		});
		panel.add(btnDiscover);		
	
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mib = new MIB(txtIptext.getText(),Integer.parseInt(txtPorttext.getText()));				
				Thread read = new Thread( new Reader(mib) );
				read.start();

			}
		});
		panel.add(btnConnect);
		
		JButton btnGetservices = new JButton("GetServices");
		btnGetservices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mib.getURLList("*");
				for (int x=0; x< mib.services.getMatches().size(); x++) {
					Match m = mib.services.getMatches().get(x);
					System.out.println("Add " + m.getUrl());
					addServicesToUI(m.getUrl());
				}
				panel_1.revalidate();
				panel_1.repaint();

			}
		});
		panel.add(btnGetservices);
		
		JButton btnDisconnect = new JButton("Disconnect");
		panel.add(btnDisconnect);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);				
			}
		});
		panel.add(btnClose);
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);		
		
		
		
		javax.swing.Timer t = new javax.swing.Timer( 100, new ActionListener() {
			  public void actionPerformed( ActionEvent e ) {

				  
				if (mib != null) {
					Component[] componentList = panel_2.getComponents();
					for (Component c : componentList) {
						panel_2.remove(c);
					}

					for (int x = 0; x < mib.data.size(); x++) {
						if (mib.data.get(x).getData().get(0) instanceof Abs) {
							JLabel lblNewLabel = new JLabel(mib.data.get(x).getUrl());
							panel_2.add(lblNewLabel);

							Abs abs = (Abs) mib.data.get(x).getData().get(0);

							JTextField textField = new JTextField();
							textField.setText(abs.getVal());
							panel_2.add(textField);
							textField.setColumns(10);

						}
					}

					panel_2.revalidate();
					panel_2.repaint();
				}
			  }
			});
			t.start(); 
		
		
	}
	
	
	private void addServicesToUI(String url) {
		
		JCheckBox chckbxNewCheckBox = new JCheckBox(url);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				if (selected) {
					mib.subscribeToUrl(e.getActionCommand());
				} else {
					mib.unSubscribeToUrl(e.getActionCommand());
				}
			}
		});
		panel_1.add(chckbxNewCheckBox);
		
	}
	

}
