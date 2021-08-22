package a.entity.gus.b.appview2.gui3.entity;

import java.io.File;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210822";}


	private File location;
	private List list;
	
	public EntityImpl() throws Exception {
		
	}
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		location = (File) o[0];
		list = (List) o[1];
	}
	
	
	public Object i() throws Exception {
		return null;
	}
}
