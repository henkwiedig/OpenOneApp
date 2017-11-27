package com.openoneapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JLabel;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import com.openoneapp.protocol.server.ServiceBeacon;

public class ServiceBeaconListener {

	private Persister persister = new Persister(new AnnotationStrategy());
	private Serializer serializer = persister;
	private ServiceBeacon sb;
		
	
	public String getBeacon() {
		try {
			DatagramSocket serverSocket = new DatagramSocket(28500);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				System.out.println("Read from network: " + sentence);
				// Deserialize ServiceBeacon
				sb = serializer.read(ServiceBeacon.class, sentence);
				System.out.print("Parsed: ");
				serializer.write(sb, System.out);
				System.out.println("\n------------\n");
				return sb.getAddress();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}

}
