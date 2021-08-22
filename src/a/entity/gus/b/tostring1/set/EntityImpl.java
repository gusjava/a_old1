package a.entity.gus.b.tostring1.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}


	private Service listToString;

	public EntityImpl() throws Exception
	{
		listToString = Outside.service(this,"gus.a.tostring.list");
	}

	public Object t(Object obj) throws Exception
	{return setToString((Set) obj);}
	
	
	private String setToString(Set set) throws Exception
	{
		List list = new ArrayList(set);
		Collections.sort(list);
		return (String) listToString.t(list);
	}
}