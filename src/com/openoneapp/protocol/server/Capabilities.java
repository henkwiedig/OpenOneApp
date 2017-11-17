package com.openoneapp.protocol.server;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Capabilities")
public class Capabilities {
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public Supports getSupports() {
		return supports;
	}
	public void setSupports(Supports supports) {
		this.supports = supports;
	}
	
	@Attribute
	private String description;
	
	@Attribute
	private String service;
	
	@Attribute
	private String version;
	
	@Attribute
	private String id;
	
	@Element(name="Supports")
	private Supports supports;


}
