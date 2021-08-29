package a.entity.gus.b.entitysrc1.rootdir;

import java.io.File;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210806";}

	private Service appLocation;
	private Service manager;
	
	private File rootDir;
	
	public EntityImpl() throws Exception
	{
		appLocation = Outside.service(this,"gus.a.app.location");
		manager = Outside.service(this,"gus.b.entitysrc1.rootdir.manager");
	}
	
	
	public Object g() throws Exception
	{
		if(rootDir==null) rootDir = build();
		return rootDir;
	}
	
	
	private File build() throws Exception
	{
		String rootPath = (String) manager.g();
		if(rootPath!=null) return new File(rootPath);

		File location = (File) appLocation.g();
		File parentDir = location.getParentFile();
		
		File srcDir1 = new File(parentDir, "src");
		if(srcDir1.isDirectory()) return srcDir1;
		
		File srcDir2 = new File(parentDir.getParentFile(), "src");
		if(srcDir2.isDirectory()) return srcDir2;
		
		File userDir = new File(System.getProperty("user.dir"));
		return new File(userDir, "src");
	}
}
