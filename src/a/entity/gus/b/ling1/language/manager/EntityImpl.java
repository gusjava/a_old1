package a.entity.gus.b.ling1.language.manager;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, P {

	public String creationDate() {return "20210814";}


	private Service langInit;
	private Service persister1;
	
	private String lang;
	private String persistKey = getClass().getName()+"_lang";
	
	
	public EntityImpl() throws Exception
	{
		langInit = Outside.service(this,"gus.b.ling1.language.init");
		persister1 = Outside.service(this,"gus.b.persist1.main");
		
		lang = (String) persister1.r(persistKey);
		if(lang==null) lang = (String) langInit.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		String newLang = (String) obj;
		if(newLang==null) throw new Exception("Invalid null value for newLang");
		
		if(lang.equals(newLang)) return;
		
		lang = newLang;
		persister1.v(persistKey, lang);
		langChanged();
	}
	
	
	public Object g() throws Exception
	{return lang;}
	
	
	private void langChanged()
	{send(this,"langChanged()");}
}
