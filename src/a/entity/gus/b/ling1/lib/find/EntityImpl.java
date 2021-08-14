package a.entity.gus.b.ling1.lib.find;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, R {
	public String creationDate() {return "20210814";}


	private Service loadLib;
	private Service langManager;
	
	private Map cache;

	public EntityImpl() throws Exception
	{
		loadLib = Outside.service(this,"gus.b.ling1.lib.load");
		langManager = Outside.service(this,"gus.b.ling1.language.manager");
		
		cache = new HashMap();
	}
	
	
	
	public Object r(String key) throws Exception
	{
		String lang = (String) langManager.g();
		String libName = key+"_"+lang;
		
		if(cache.containsKey(libName))
			return cache.get(libName);
		
		Map resource = (Map) loadLib.r(libName);
		if(resource==null) return null;
		
		cache.put(libName, resource);
		return resource;
	}
}