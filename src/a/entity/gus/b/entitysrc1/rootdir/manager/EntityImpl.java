package a.entity.gus.b.entitysrc1.rootdir.manager;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P {
	public String creationDate() {return "20210816";}

	private Service persister1;
	private String persistKey = getClass().getName()+"_root";
	
	
	public EntityImpl() throws Exception
	{
		persister1 = Outside.service(this,"gus.b.persist1.main");
	}
	
	public Object g() throws Exception
	{
		return (String) persister1.r(persistKey);
	}
	
	public void p(Object obj) throws Exception
	{
		persister1.v(persistKey,  (String) obj);
	}
}
