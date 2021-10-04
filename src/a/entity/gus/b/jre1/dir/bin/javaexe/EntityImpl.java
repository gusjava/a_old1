package a.entity.gus.b.jre1.dir.bin.javaexe;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20211004";}

	private Service bin;
	private File javaExe;
	
	public EntityImpl() throws Exception
	{
		bin = Outside.service(this,"gus.b.jre1.dir.bin");
	}
	
	public Object g() throws Exception
	{
		if(javaExe==null) init();
		return javaExe;
	}
	
	private void init() throws Exception
	{
		File binDir = (File) bin.g();
		dir = new File(binDir,"java.exe");
	}
}