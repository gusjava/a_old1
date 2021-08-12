package a.entity.gus.b.icons1.loader;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T, F, R {
	public String creationDate() {return "20210812";}


	private Service iconInside;
	private Service iconOutside;

	public EntityImpl() throws Exception
	{
		iconInside = Outside.service(this,"m033.t.config.load.icon");
		iconOutside = Outside.service(this,"gus.b.icons1.loader.outside.main");
	}
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	public Object r(String key) throws Exception
	{
		Icon icon1 = (Icon) iconOutside.t(key);
		if(icon1!=null) return icon1;
		return (Icon) iconInside.t(key);
	}
	
	public boolean f(Object obj) throws Exception
	{return iconInside.f(obj) || iconOutside.f(obj);}
}