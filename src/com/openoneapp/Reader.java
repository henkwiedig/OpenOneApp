package com.openoneapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;


public class Reader implements Runnable {
	
	MIB mib;
	
	public Reader(MIB m) {
		super();
		mib = m;
	}

	@Override
	public void run() {
	    try {
			String data = new String();
			String line;
			while((line = mib.br.readLine()) != null) {
				data += line;
				System.out.println("Read from network: " + data);
				if ( data.matches(".*</Status>.*")){
					mib.handleStatusMessage(data);
					data = "";
				} else if (data.matches(".*</Rsp>.*|.*<Rsp id=\"\\d*\"/>.*")) {
					mib.handleRspMessage(data);
					data = "";
				} else if (data.matches(".*</Dat>.*")){
					mib.handleDatMesage(data);
					data = "";
				}
			}
	    } catch (SocketException e) {
	    	System.out.println("Socket closed!");
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
