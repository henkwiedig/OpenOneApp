package com.openoneapp.protocol.server;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import com.openoneapp.protocol.EmptyElementConverter;

@Root(name="Status")
public class Status {
	
    public String getInit() {
		return Init;
	}

	public void setInit(String init) {
		Init = init;
	}

	@Element(required=false)
	@Convert(value = EmptyElementConverter.class)
	private String Init;

}
