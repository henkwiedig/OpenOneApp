package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Dat")
public class Dat {
	
	@Attribute
	private String url;
	
	@Element(name="Txt",required=false)
	private Txt txt;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Txt getTxt() {
		return txt;
	}

	public void setTxt(Txt txt) {
		this.txt = txt;
	}
	
	
	

}
