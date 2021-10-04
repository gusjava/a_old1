package a.entity.gus.b.jre1.dir.bin;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20211004";}

	private Service home;
	private File dir;
	
	public EntityImpl() throws Exception
	{
		home = Outside.service(this,"gus.a.jre.prop.javahome.as.file");
	}
	
	public Object g() throws Exception
	{
		if(dir==null) init();
		return dir;
	}
	
	private void init() throws Exception
	{
		File homeDir = (File) home.g();
		dir = new File(homeDir,"bin");
	}
}