package a.entity.gus.b.ling1.msg.find;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, R, F {
	public String creationDate() {return "20210814";}
	
	public static final String DEFAULT = "default";


	private Service findLib;

	public EntityImpl() throws Exception
	{
		findLib = Outside.service(this,"gus.b.ling1.lib.find");
	}
	
	
	public Object r(String key) throws Exception {
		if(!key.startsWith("m_")) return key;
		try{return find(key);}
		catch(Exception e)
		{
			String message = "Ling key localization failed: "+key;
			throw new Exception(message,e);
		}
	}
	
	public boolean f(Object obj) throws Exception {
		String key = (String) obj;
		if(!key.startsWith("m_")) return false;
		
		String[] info = analyze(key);
		String name = info[0];
		String lingKey = info[1];
		
		Map map = (Map) findLib.r(name);
		
		if(map==null) return false;
		if(!map.containsKey(lingKey)) return false;
		String value = (String) map.get(lingKey);
		if(value.equals("")) return false;
		
		return true;
	}
	
	
	
	private String find(String key) throws Exception
	{
		String[] info = analyze(key);
		String name = info[0];
		String lingKey = info[1];
		
		Map map = (Map) findLib.r(name);
		
		if(map==null) return "{"+key+"}";
		if(!map.containsKey(lingKey)) return "["+key+"]";
		String value = (String) map.get(lingKey);
		if(value.equals("")) return "("+key+")";
		
		return value;
	}
	

	
	private String[] analyze(String key)
	{
		key = key.substring(2);
		if(!key.contains("_")) return new String[]{DEFAULT, key};
		return key.split("_",2);
	}
}
