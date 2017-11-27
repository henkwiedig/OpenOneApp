package com.openoneapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServiceBeaconListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatagramSocket serverSocket;
		try {
			serverSocket = new DatagramSocket(28500);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				String sentence = new String(receivePacket.getData());
				System.out.println("RECEIVED: ->" + sentence + "<--");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
