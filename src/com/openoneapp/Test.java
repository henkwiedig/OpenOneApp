package com.openoneapp;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import com.openoneapp.protocol.client.Abs;
import com.openoneapp.protocol.client.Elem;
import com.openoneapp.protocol.client.MyList;
import com.openoneapp.protocol.client.Txt;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Persister persister = new Persister(new AnnotationStrategy());
		Serializer serializer = persister;
		
		Abs abs = new Abs();
		abs.setName("WarnID");
		abs.setVal("43008");

		
		Txt txt = new Txt();
		txt.setName("dynamicValue");
		txt.setVal("");
		
		ArrayList list = new ArrayList();
		list.add(abs);
		list.add(txt);
		
		Elem elem = new Elem();
		elem.setData(list);
		
		MyList mylist = new MyList();
		mylist.setName("Warming");
		
		ArrayList elemlist = new ArrayList();
		elemlist.add(elem);
		elemlist.add(elem);
		mylist.setElem(elemlist);

		
		
		try {
			serializer.write(mylist,System.out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
