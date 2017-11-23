package com.openoneapp;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleReader implements Runnable {
	
	MIB mib;

	public ConsoleReader(MIB m) {
		super();
		mib = m;
	}

	@Override
	public void run() {
		
	    Scanner sc = new Scanner(System.in).useDelimiter("\\s*");
	    while (!sc.hasNext("q")) {
	        char ch = sc.next().charAt(0);
	        if (ch == 'v') {
	        	System.out.println("Get VIN");
	        	
	        }
	        if (ch == 'd') {
	        	System.out.println("Get Dirlist");
	        	mib.getURLList("*");
	        	
	        }
        
        }
	    try {
			mib.car.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

}
