package a.entity.gus.b.ling1.language.init;

import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210814";}

	public static final String KEY_INIT = "app.langinit";

	private Service langList;
	private Map prop;
	private String value;
	
	public EntityImpl() throws Exception
	{
		langList = Outside.service(this,"gus.b.ling1.language.list");
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public Object g() throws Exception
	{
		if(value==null) value = init();
		return value;
	}
	
	
	private String init() throws Exception
	{
		List available = (List) langList.g();
	
		String lang1 = get(KEY_INIT);
		String lang2 = userLang();
		
		if(lang1!=null && available.contains(lang1)) return lang1;
		if(lang2!=null && available.contains(lang2)) return lang2;
		
		throw new Exception("Unable to initialize language value");
	}
	
	
	private String get(String key)
	{return prop.containsKey(key)?(String) prop.get(key):null;}
	
	private String userLang()
	{return System.getProperty("user.language");}
}
