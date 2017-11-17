package com.openoneapp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import com.openoneapp.protocol.client.Authenticate;
import com.openoneapp.protocol.client.Protocol;
import com.openoneapp.protocol.client.Req;
import com.openoneapp.protocol.client.Subscribe;
import com.openoneapp.protocol.client.Dat;
import com.openoneapp.protocol.server.Rsp;
import com.openoneapp.protocol.server.Status;

public class main {
	
	
    private static String user = "OneApp-935000";
    private static String password = "h4Ztcox+b5KmWc0bu2w3htrJ5xk9LDNnV5RSPqJsaCY=";

	public static void main(String[] args) {
		Persister persister = new Persister(new AnnotationStrategy());
		Serializer serializer = persister;
		int requestCounter = 1;
		
		
		try {					
			//Socket car = new Socket("10.173.189.1", 25010);
			Socket car = new Socket("localhost", 25010);

			//Connection phase
			InputStream in = car.getInputStream();
			OutputStream out = car.getOutputStream();
			
			//Read Status Packet
			Status status = serializer.read(Status.class, in);
			System.out.print("Answer: ");
			serializer.write(status,System.out);
			System.out.println("\n------------\n");
			
			//Answer with Protocol
			Req req = new Req();
			req.setId(requestCounter++);
			Protocol protocol = new Protocol();
			protocol.setVersion("1");
			protocol.setReturnCapabilities("true");
			req.setProtocol(protocol);
			System.out.print("Sending: ");
			serializer.write(req,out);
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
			req = null;
			
			//wait for answer
			Rsp rsp = serializer.read(Rsp.class, in);
			System.out.print("Answer: ");
			serializer.write(rsp,System.out);
			System.out.println("\n------------\n");
			rsp = null;
			
			//Initiate challange
			req = new Req();
			req.setId(requestCounter++);
			Authenticate authenticate = new Authenticate();
			authenticate.setPhase("challenge");
			authenticate.setuseHash("sha256");
			req.setAuthenticate(authenticate);
			System.out.print("Sending: ");
			serializer.write(req,out);
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
			req = null;
			authenticate = null;
			
			//wait for nonce
			rsp = serializer.read(Rsp.class, in);
			System.out.print("Answer: ");
			serializer.write(rsp,System.out);
			System.out.println("\n------------\n");
			
			//do challange magic
	        byte[] b = new byte[16];
	        new Random().nextBytes(b);
	        	        
	        String cnonce = Base64.getEncoder().encodeToString(b);
            String digest = Base64.getEncoder().encodeToString(sha256(user + ":" + password + ":" + rsp.getChallenge().getNonce() + ":" + cnonce));

			//answer challange
			req = new Req();
			req.setId(requestCounter++);
			authenticate = new Authenticate();
			authenticate.setPhase("response");
			authenticate.setUser(user);
			authenticate.setCnonce(cnonce);
			authenticate.setDigest(digest);
			req.setAuthenticate(authenticate);
			System.out.print("Sending: ");
			serializer.write(req,out);
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
			
			authenticate = null;
			rsp = null;
		
			//wait for answer
			rsp = serializer.read(Rsp.class, in);
			System.out.print("Answer: ");
			serializer.write(rsp,System.out);
			System.out.println("\n------------\n");
			rsp = null;
			
			//get VIN
			req = new Req();
			req.setId(requestCounter++);
			Subscribe subscribe = new Subscribe();
			//subscribe.setUrl("vehicleIdenticationNumber");
			subscribe.setUrl("seatHeater_zone2");
			subscribe.setIval("3");
			req.setSubscribe(subscribe);
			System.out.print("Sending: ");
			serializer.write(req,out);
			serializer.write(req,System.out);
			System.out.println("\n------------\n");
			
			subscribe = null;
			req=null;
			
			//wait for answer
			rsp = serializer.read(Rsp.class, in);
			System.out.print("Answer: ");
			serializer.write(rsp,System.out);
			System.out.println("\n------------\n");
			rsp = null;
			
			//wait for answer
			//Dat dat = serializer.read(Dat.class, in);
			//System.out.print("Answer: ");
			//serializer.write(dat,System.out);
			//System.out.println("\n------------\n");
			//rsp = null;
			
			int read;
		    byte[] buffer = new byte[1024];
		    while((read = in.read(buffer)) != -1) {
		        String output = new String(buffer, 0, read);
		        System.out.print(output);
		        System.out.flush();
		    };
			
			car.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
    private static byte[] sha256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            return digest.digest(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


}
