package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Protocol", strict = false)
public class Protocol {
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getReturnCapabilities() {
		return returnCapabilities;
	}

	public void setReturnCapabilities(String returnCapabilities) {
		this.returnCapabilities = returnCapabilities;
	}

	@Attribute
	private String version;
	
	@Attribute
	private String returnCapabilities;


}
