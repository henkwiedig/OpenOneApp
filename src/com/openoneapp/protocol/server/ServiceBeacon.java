package com.openoneapp.protocol.server;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="ServiceBeacon",strict=false)
public class ServiceBeacon {
	
	@Attribute
	private String id;
	
	@Attribute
	private String address;
	
	@Attribute
	private String service;

	@Attribute
	private String status;
	
	@Attribute
	private String description;

	@Attribute
	private String version;
	
	@Attribute
	private String envelope;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEnvelope() {
		return envelope;
	}

	public void setEnvelope(String envelope) {
		this.envelope = envelope;
	}
	
	

}
