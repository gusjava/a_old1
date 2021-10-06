package a.entity.gus.b.persist1.features.gp;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20211006";}


	private Service manager;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus.b.persist1.manager");
	}
	
	public void v(String key, Object obj) throws Exception
	{
		String text = (String) manager.r(key);
		if(text!=null) ((P) obj).p(text);
		manager.v(key,obj);
	}
}