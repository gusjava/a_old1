package a.entity.gus.c.devtool1.level.manager;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, P {
	public String creationDate() {return "20210815";}
	
	public static final String LEVEL1 = "level1";
	public static final String LEVEL2 = "level2";
	public static final String LEVEL3 = "level3";


	private Service persister1;
	
	private String level;
	private String persistKey = getClass().getName()+"_level";
	
	
	public EntityImpl() throws Exception
	{
		persister1 = Outside.service(this,"gus.b.persist1.main");
		
		level = (String) persister1.r(persistKey);
		if(level==null) level = LEVEL1;
	}
	
	
	public void p(Object obj) throws Exception
	{
		String newLevel = (String) obj;
		if(newLevel==null) throw new Exception("Invalid null value for newLevel");
		
		if(level.equals(newLevel)) return;
		
		level = newLevel;
		persister1.v(persistKey, level);
		levelChanged();
	}
	
	
	public Object g() throws Exception
	{return level;}
	
	
	private void levelChanged()
	{send(this,"levelChanged()");}
}
