package a.entity.gus.b.persist1.set.string;

import java.util.Set;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20210903";}


	private Service manager;
	private Service stringToSet;
	private Service setToString;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus.b.persist1.manager");
		stringToSet = Outside.service(this,"gus.b.convert1.stringtoset");
		setToString = Outside.service(this,"gus.a.tostring.set");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		final Set set = (Set) obj;
		set.clear();
		
		String text = (String) manager.r(key);
		if(text!=null)
		{
			Set set0 = (Set) stringToSet.t(text);
			set.addAll(set0);
		}
		
		manager.v(key,new G(){
			public Object g() throws Exception {return setToString.t(set);}
		});
	}
}
