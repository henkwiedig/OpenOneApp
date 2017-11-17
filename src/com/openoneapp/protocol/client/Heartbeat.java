package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Heartbeat", strict = false)
public class Heartbeat {
	
	@Attribute
	private String ival;

}
