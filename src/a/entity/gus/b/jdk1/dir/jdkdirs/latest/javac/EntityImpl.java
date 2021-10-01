package a.entity.gus.b.jdk1.dir.jdkdirs.latest.javac;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210929";}


	private Service getLatest;

	public EntityImpl() throws Exception
	{
		getLatest = Outside.service(this,"gus.b.jdk1.dir.jdkdirs.latest");
	}
	
	public Object g() throws Exception
	{
		File jdkDir = (File) getLatest.g();
		return javacFile(jdkDir);
	}
	
	private File javacFile(File jdkDir) throws Exception
	{
		File javacExe = new File(new File(jdkDir,"bin"),"javac.exe");
		if(!javacExe.isFile()) throw new Exception("Javac exe not found: "+javacExe);
		return javacExe;
	}
}
