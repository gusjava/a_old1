package a.entity.gus.b.paths1.rootdir.open;

import a.framework.*;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20210810";}

	
	private Service open;
	private Service get;

	public EntityImpl() throws Exception {
		open = Outside.service(this,"gus.a.awt.desktop.open");
		get = Outside.service(this,"gus.b.paths1.rootdir");
	}
	
	public void e() throws Exception {
		open.p(get.g());
	}
}
