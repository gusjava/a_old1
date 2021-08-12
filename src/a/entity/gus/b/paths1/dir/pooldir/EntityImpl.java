package a.entity.gus.b.paths1.dir.pooldir;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210812";}
	
	public static final String PATH = "path.pooldir";

	private Service provider;

	public EntityImpl() throws Exception {
		provider = Outside.service(this, "gus.b.paths1.provider.main");
	}
	
	public Object g() throws Exception {
		return provider.r(PATH);
	}
}
