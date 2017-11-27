package com.openoneapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ServiceBeaconSender implements Runnable {
	
	private boolean Running;

	public boolean isRunning() {
		return Running;
	}

	public void setRunning(boolean running) {
		Running = running;
	}

	@Override
	public void run() {
		try {
			Running = true;
			while (Running) {
				for (Enumeration<NetworkInterface> niEnum = NetworkInterface
						.getNetworkInterfaces(); niEnum.hasMoreElements();) {
					NetworkInterface ni = niEnum.nextElement();
					if (!ni.isLoopback()) {
						for (InterfaceAddress interfaceAddress : ni
								.getInterfaceAddresses()) {
							if (interfaceAddress.getBroadcast() != null) {
								InetAddress ia = interfaceAddress
										.getBroadcast();
								String s = "<ServiceBeacon id=\"DGTx0NBHjv5MRWviWb8Z4bURaVYNaXmQ\" address=\"socket://"+ interfaceAddress.getAddress().getHostAddress() +":25010\" service=\"VW SAI-Server HIGH\" status=\"active\" description=\"Dies ist der VW Standard Application Interface Server. API Level 5\" version=\"5.0\" envelope=\"pretty\"/>";
								byte[] data = s.getBytes();
								DatagramPacket packet = new DatagramPacket(
										data, data.length, ia, 28500);
								DatagramSocket toSocket = new DatagramSocket();
								toSocket.send(packet);
							}
						}
					}
				}
			Thread.sleep(4000);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
