package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Authenticate", strict = false)
public class Authenticate {
	
	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getuseHash() {
		return useHash;
	}

	public void setuseHash(String sha256) {
		this.useHash = sha256;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCnonce() {
		return cnonce;
	}

	public void setCnonce(String cnonce) {
		this.cnonce = cnonce;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	@Attribute
	private String phase;
	
	@Attribute(required = false)
	private String useHash;
	
	@Attribute(required = false)
	private String user;
	
	@Attribute(required = false)
	private String cnonce;
	
	@Attribute(required = false)
	private String digest;

}
