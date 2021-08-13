package a.entity.gus.b.tostring1.desc.short1;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}


	public EntityImpl() throws Exception {
		
	}
	
	
	public Object t(Object obj) throws Exception {
		if(obj==null) return "null";
		
		//TODO ...
		return obj.getClass().getSimpleName();
	}
}
