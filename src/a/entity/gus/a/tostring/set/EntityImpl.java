package a.entity.gus.a.tostring.set;

import java.util.Iterator;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210903";}


	public Object t(Object obj) throws Exception
	{return setToString((Set) obj);}
	
	
	
	private String setToString(Set set) throws Exception
	{
		StringBuffer b = new StringBuffer();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String el = (String) it.next();
			if(el.contains("\n")) throw new Exception("Invalid element syntax: "+el);
			b.append(el+"\n");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}