package a.entity.gus.b.dataview1.object.panel.class1;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210811";}

	
	private Service viewer;

	public EntityImpl() throws Exception {
		viewer = Outside.service(this,"*gus.b.dataview1.class1");
	}
	
	public void p(Object obj) throws Exception {
		viewer.p(obj);
	}
	
	public Object i() throws Exception {
		return viewer.i();
	}
}
