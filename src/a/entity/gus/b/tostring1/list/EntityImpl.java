package a.entity.gus.b.tostring1.list;

import java.util.List;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}


	public Object t(Object obj) throws Exception
	{return listToString((List) obj);}
	
	
	
	private String listToString(List list) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		{
			String el = (String) list.get(i);
			if(el.contains("\n")) throw new Exception("Invalid element syntax: "+el);
			b.append(el+"\n");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}