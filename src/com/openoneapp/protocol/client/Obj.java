package com.openoneapp.protocol.client;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root(name="Obj")
public class Obj {
	
	@Attribute(required=false)
	private String url;
	
	@Attribute(required=false)
	private String name;
	
	@ElementListUnion({
	@ElementList(entry="Act", type=Act.class, inline=true, required=false),
	@ElementList(entry="Enm", type=Enm.class, inline=true, required=false)
	})
	private List<Object> data;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
	
	

}
