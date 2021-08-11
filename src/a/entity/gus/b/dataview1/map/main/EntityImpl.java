package a.entity.gus.b.dataview1.map.main;

import java.util.Map;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210811";}

	private Service viewer;
	private Map main;

	public EntityImpl() throws Exception {
		viewer = Outside.service(this,"*gus.b.dataview1.map");
		main = (Map) Outside.resource(this,"main");
		viewer.p(main);
	}
	
	public Object i() throws Exception {
		return viewer.i();
	}
}
