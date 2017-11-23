package com.openoneapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import com.openoneapp.protocol.client.Authenticate;
import com.openoneapp.protocol.client.Dat;
import com.openoneapp.protocol.client.Dir;
import com.openoneapp.protocol.client.Protocol;
import com.openoneapp.protocol.client.Req;
import com.openoneapp.protocol.client.Subscribe;
import com.openoneapp.protocol.server.Match;
import com.openoneapp.protocol.server.Rsp;

public class MIB {

	
    private String user = "OneApp-935000";
    private String password = "h4Ztcox+b5KmWc0bu2w3htrJ5xk9LDNnV5RSPqJsaCY=";	
	private Persister persister = new Persister(new AnnotationStrategy());
	private Serializer serializer = persister;
	private int requestCounter = 1;
	public Socket car;
	public BufferedReader br;	
	
	public MIB () {
		
		try {
			//car = new Socket("10.173.189.1", 25010);
			car = new Socket("localhost", 25010);
			br = new BufferedReader(new InputStreamReader(car.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}	
	
	public void handleStatusMessage(String message){
		
		try {
			System.out.println("Read Status Message: " + message);
			
			//Answer with Protocol
			Req req = new Req();
			req.setId(requestCounter++);
			Protocol protocol = new Protocol();
			protocol.setVersion("1");
			protocol.setReturnCapabilities("true");
			req.setProtocol(protocol);
			System.out.print("Sending: ");
			serializer.write(req,this.car.getOutputStream());
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void handleRspMessage(String message) {
		
		System.out.println("handleRspMessage got -->" + message + "<---");
		
		try {
			//Deserialize Response
			Rsp rsp = serializer.read(Rsp.class, message);
			System.out.print("Answer: ");
			serializer.write(rsp,System.out);
			System.out.println("\n------------\n");
			
			//Lets check the message types
			if (rsp.getCapabilities() != null) this.startAuth();
			if (rsp.getChallenge() != null) this.completeAuth(rsp);
			if (rsp.getMatches() != null) this.handleMatches(rsp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void handleMatches(Rsp rsp) {
		
		ArrayList<Match> matches = rsp.getMatches();
		
		for (int x=0; x<matches.size(); x++) {
			Match m = matches.get(x);
			this.subscribeToUrl(m.getUrl());			
		}

		
	}

	public void handleDatMesage(String message) {
		
		try {
			//Deserialize Dat
			Dat dat = serializer.read(Dat.class, message);
			System.out.print("Answer: ");
			serializer.write(dat,System.out);
			System.out.println("\n------------\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void subscribeToUrl(String url){
		
		try {
			Req req = new Req();
			req.setId(requestCounter++);			
			Subscribe subscribe = new Subscribe();
			subscribe.setUrl(url);
			req.setSubscribe(subscribe);			
			System.out.print("Sending: ");
			serializer.write(req,car.getOutputStream());
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getURLList(String pattern) {
		
		try {
			Req req = new Req();
			req.setId(requestCounter++);
			Dir dir = new Dir();
			dir.setUrlPattern(pattern);
			dir.setFromEntry("1");
			dir.setNumOfEntries("999999999");
			req.setDir(dir);
			System.out.print("Sending: ");
			serializer.write(req,car.getOutputStream());
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void startAuth(){
		//Initiate challange
		try {
			Req req = new Req();
			req.setId(requestCounter++);
			Authenticate authenticate = new Authenticate();
			authenticate.setPhase("challenge");
			authenticate.setuseHash("sha256");
			req.setAuthenticate(authenticate);
			System.out.print("Sending: ");
			serializer.write(req,car.getOutputStream());
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
			req = null;
			authenticate = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void completeAuth(Rsp rsp) {

		try {
			// do challange magic
			byte[] b = new byte[16];
			new Random().nextBytes(b);
			
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();

			String cnonce = Base64.getEncoder().encodeToString(b);
			String digest = Base64.getEncoder().encodeToString(
					md.digest((user + ":" + password + ":"
							+ rsp.getChallenge().getNonce() + ":" + cnonce)
							.getBytes()));

			// answer challange
			Req req = new Req();
			req.setId(requestCounter++);
			Authenticate authenticate = new Authenticate();
			authenticate.setPhase("response");
			authenticate.setUser(user);
			authenticate.setCnonce(cnonce);
			authenticate.setDigest(digest);
			req.setAuthenticate(authenticate);
			System.out.print("Sending: ");
			serializer.write(req, this.car.getOutputStream());
			serializer.write(req, System.out);
			System.out.println("\n------------\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
