package a.entity.gus.b.jdk1.dir.root;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210929";}


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
		
		dir = homeDir.getParentFile();
    		while(dir!=null && !isJavaRoot(dir))
    		dir = dir.getParentFile();
		if(dir==null) throw new Exception("Java root directory does not exist: "+dir);
	}
	
	
	private boolean isJavaRoot(File dir)
	{return dir.getName().toLowerCase().equals("java");}
}
