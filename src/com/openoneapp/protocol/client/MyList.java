package com.openoneapp.protocol.client;


import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root(name="List")
public class MyList {
	
	@Attribute
	private String name;
	
	@Attribute(required=false)
	private String state;
	
	@ElementListUnion({
	@ElementList(entry="Elem", type=Elem.class, inline=true, required=false)
	})
	private List<Object> elem;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Object> getElem() {
		return elem;
	}

	public void setElem(List<Object> elem) {
		this.elem = elem;
	}
	
	

}
