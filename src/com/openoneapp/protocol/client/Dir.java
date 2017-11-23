package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Dir", strict = false)
public class Dir {
	
	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getFromEntry() {
		return fromEntry;
	}

	public void setFromEntry(String fromEntry) {
		this.fromEntry = fromEntry;
	}

	public String getNumOfEntries() {
		return numOfEntries;
	}

	public void setNumOfEntries(String numOfEntries) {
		this.numOfEntries = numOfEntries;
	}

	@Attribute
	private String urlPattern;
	
	@Attribute
	private String fromEntry;
	
	@Attribute
	private String numOfEntries;

}
