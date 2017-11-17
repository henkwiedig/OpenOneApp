package com.openoneapp.protocol.server;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.openoneapp.protocol.client.Dat;


@Root(name="Rsp",strict=false)
public class Rsp {
	
	@Attribute
	private int id;
	
	@Element(name="Capabilities",required = false)
	private Capabilities capabilities;
	
	@Element(name="Challenge",required = false)
	private Challenge challenge;
	
	@Element(name="Dat",required = false)
	private Dat dat;
	
	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public Capabilities getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(Capabilities capabilities) {
		this.capabilities = capabilities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Dat getDat() {
		return dat;
	}

	public void setDat(Dat dat) {
		this.dat = dat;
	}
	
	

}
