package a.entity.gus.b.entityclass1.jardir.init;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20210820";}

	
	private File jarDir;

	public EntityImpl() throws Exception {
		jarDir = (File) Outside.resource(this,"path#path.jardir");
	}
	
	
	public void e() throws Exception {
		
	}
}
