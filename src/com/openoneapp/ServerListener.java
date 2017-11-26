package com.openoneapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener implements Runnable {

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(25010);
			Socket client = serverSocket.accept();
			
			OutputStream out = client.getOutputStream();
			String hello = "<Status>\n" +
					"  <Init/>\n" +
					"</Status>\n";
			out.write(hello.getBytes());
			out.flush();			
			BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
			   for (String line = br.readLine(); line != null; line = br.readLine()) {
			       System.out.println(line);
			    }
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
