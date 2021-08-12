package a.entity.gus.b.files1.ext.sample;

import java.io.File;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private File tmpDir;
	
	public EntityImpl() throws Exception
	{
		tmpDir = (File) Outside.resource(this,"defaultdir");
		tmpDir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{return tmpDir;}
	
	
	public Object r(String key) throws Exception
	{return t(key);}
	
	
	public Object t(Object obj) throws Exception
	{
		String ext = (String) obj;
		String fileName = ext.equals("")?"tmp":"tmp."+ext;
		
		File file = new File(tmpDir,fileName);
		if(!file.exists()) file.createNewFile();
		return file;
	}
}