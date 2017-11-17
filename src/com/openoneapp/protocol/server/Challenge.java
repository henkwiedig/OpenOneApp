package com.openoneapp.protocol.server;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Challenge")
public class Challenge {
	
	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	@Attribute
	private String nonce;

}
