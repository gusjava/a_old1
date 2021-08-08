package a.entity.gus.b.entitysrc1.rootdir;

import java.io.File;

import a.framework.Entity;
import a.framework.G;

public class EntityImpl implements Entity, G {
	
	public String creationDate() {
		return "20210806";
	}
	
	private File rootDir;
	
	public EntityImpl() throws Exception {
		File userDir = new File(System.getProperty("user.dir"));
		rootDir = new File(userDir, "src");
	}
	
	public Object g() throws Exception {
		return rootDir;
	}
}
