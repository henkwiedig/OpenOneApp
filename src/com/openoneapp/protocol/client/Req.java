package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Req", strict = false)
public class Req {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public Heartbeat getHeartbeat() {
		return heartbeat;
	}

	public void setHeartbeat(Heartbeat heartbeat) {
		this.heartbeat = heartbeat;
	}

	public Authenticate getAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}

	public Subscribe getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	

	public Unsubscribe getUnsubscribe() {
		return unsubscribe;
	}

	public void setUnsubscribe(Unsubscribe unsubscribe) {
		this.unsubscribe = unsubscribe;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	@Attribute
	private int id;
	
	@Element(name="Protocol",required=false)
	private Protocol protocol;
		
	@Element(name="Heartbeat",required=false)
	private Heartbeat heartbeat;
	
	@Element(name="Authenticate",required=false)
	private Authenticate authenticate;
	
	@Element(name="Subscribe",required=false)
	private Subscribe subscribe;
	
	@Element(name="Unsubscribe",required=false)
	private Unsubscribe unsubscribe;
	
	@Element(name="Dir",required=false)
	private Dir dir;

}
