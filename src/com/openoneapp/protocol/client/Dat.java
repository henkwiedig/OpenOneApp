package com.openoneapp.protocol.client;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.ElementList;


@Root(name="Dat")
public class Dat {
	
	@Attribute
	private String url;
	
	@Element(name="List",required=false)
	private MyList list;
	
	@ElementListUnion({
	@ElementList(entry="Txt", type=Txt.class, inline=true, required=false),
	@ElementList(entry="Act", type=Act.class, inline=true, required=false),
	@ElementList(entry="Abs", type=Abs.class, inline=true, required=false),
	@ElementList(entry="Enm", type=Enm.class, inline=true, required=false),
	@ElementList(entry="Rel", type=Rel.class, inline=true, required=false),
	@ElementList(entry="Tim", type=Tim.class, inline=true, required=false),
	@ElementList(entry="Obj", type=Obj.class, inline=true, required=false)
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
