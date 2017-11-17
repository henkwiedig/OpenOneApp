package com.openoneapp.protocol.server;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Supports")
public class Supports {
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getIsinterface() {
		return isinterface;
	}
	public void setIsinterface(String isinterface) {
		this.isinterface = isinterface;
	}
	public String getAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(String authenticate) {
		this.authenticate = authenticate;
	}
	public String getHeartbeat() {
		return heartbeat;
	}
	public void setHeartbeat(String heartbeat) {
		this.heartbeat = heartbeat;
	}
	public String getDatTimeStamp() {
		return datTimeStamp;
	}
	public void setDatTimeStamp(String datTimeStamp) {
		this.datTimeStamp = datTimeStamp;
	}
	
	@Attribute
	private String protocol;
	
	@Attribute(name="interface")
	private String isinterface;
	
	@Attribute
	private String authenticate;
	
	@Attribute
	private String heartbeat;
	
	@Attribute
	private String datTimeStamp;

}
