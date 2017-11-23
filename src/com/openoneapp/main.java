package com.openoneapp;

public class main {
	
	
	public static void main(String[] args) {
		
		try {
			
			MIB mib = new MIB();
			
			Thread read = new Thread( new Reader(mib) );
			read.start();
			
			Thread readconsole = new Thread( new ConsoleReader(mib) );
			readconsole.start();
			
			readconsole.join();
			read.join();
			
			System.out.println("bye, bye");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
