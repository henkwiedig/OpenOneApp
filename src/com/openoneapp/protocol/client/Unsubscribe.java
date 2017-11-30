package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Unsubscribe")
public class Unsubscribe {
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIval() {
		return ival;
	}

	public void setIval(String ival) {
		this.ival = ival;
	}
	
	@Attribute
	private String url;
	
	@Attribute(required = false)
	private String ival;
	

}
