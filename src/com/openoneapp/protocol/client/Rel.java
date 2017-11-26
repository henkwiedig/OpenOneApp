package com.openoneapp.protocol.client;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="Rel")
public class Rel {
	
	@Attribute
	private String name;
	
	@Attribute(required=false)
	private String val;
	
	@Attribute(required=false)
	private String state;
	
	@Attribute(required=false)
	private String msg;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
