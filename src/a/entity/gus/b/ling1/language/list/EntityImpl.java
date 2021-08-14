package a.entity.gus.b.ling1.language.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210814";}

	public static final String KEY = "app.langs";

	private Map prop;
	private List list;
	
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public Object g() throws Exception
	{
		if(list==null) init();
		return list;
	}
	
	
	
	private void init() throws Exception
	{
		if(!prop.containsKey(KEY))
			throw new Exception("Property not found: "+KEY);
		String value = (String) prop.get(KEY);
		
		list = new ArrayList();
		String[] n = value.split(";");
		for(String s:n) list.add(s);
	}
}
