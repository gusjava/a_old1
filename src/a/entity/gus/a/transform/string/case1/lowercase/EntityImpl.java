package a.entity.gus.a.transform.string.case1.lowercase;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210806";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.toLowerCase();
	}
}
