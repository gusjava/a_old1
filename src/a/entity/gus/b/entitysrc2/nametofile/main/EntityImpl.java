package a.entity.gus.b.entitysrc2.nametofile.main;

import java.io.File;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}

	private Service nameToFile;
	private File rootDir;
	
	public EntityImpl() throws Exception
	{
		nameToFile = Outside.service(this,"gus.a.entity.src.find.entityfile");
		rootDir = (File) Outside.resource(this,"g#gus.b.entitysrc1.rootdir");
	}
	
	public Object t(Object obj) throws Exception
	{
		String entityName = (String) obj;
		if(rootDir==null) return null;
		return nameToFile.t(new Object[] {rootDir, entityName});
	}
}
