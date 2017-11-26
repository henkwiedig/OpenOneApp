package com.openoneapp.protocol.client;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

@Root(name="Elem")
public class Elem {
	
	@ElementListUnion({
	@ElementList(entry="Txt", type=Txt.class, inline=true, required=false),
	@ElementList(entry="Act", type=Act.class, inline=true, required=false),
	@ElementList(entry="Abs", type=Abs.class, inline=true, required=false),
	@ElementList(entry="Enm", type=Enm.class, inline=true, required=false),
	@ElementList(entry="Rel", type=Rel.class, inline=true, required=false)
	})
	private List<Object> data;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
	
	

}
