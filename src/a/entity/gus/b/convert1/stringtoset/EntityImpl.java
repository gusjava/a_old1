package a.entity.gus.b.convert1.stringtoset;

import java.util.HashSet;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210903";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		Set set = new HashSet();
		
		if(s.equals("")) return set;
		
		String[] nn = s.trim().split("\n");
		for(String n : nn) set.add(n);
        
		return set;
	}
}