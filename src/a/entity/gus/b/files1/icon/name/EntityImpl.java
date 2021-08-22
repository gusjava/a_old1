package a.entity.gus.b.files1.icon.name;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T, R {
	public String creationDate() {return "20210820";}


	
	private Service findExt;
	private Service extToIcon;
	
	public EntityImpl() throws Exception
	{
		findExt = Outside.service(this,"gus.a.file.getext");
		extToIcon = Outside.service(this,"gus.b.files1.icon.ext");
	}
	
	
	public Object r(String key) throws Exception
	{return t(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String ext = (String) findExt.t(obj);
		return extToIcon.t(ext);
	}
}