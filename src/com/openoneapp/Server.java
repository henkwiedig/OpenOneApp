package com.openoneapp;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Thread sb = new Thread( new ServiceBeaconSender() );
			sb.start();
			
			Thread server = new Thread( new ServerListener() );
			server.start();
			
			sb.join();
			sb.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
