package a.entity.gus.b.entityclass1.cl.classpath;

import java.io.File;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210820";}

	
	private Service initDir;
	private Service dirToFiles;
	private Service appLocation;
	private File jarDir;
	
	private File[] classpath;

	public EntityImpl() throws Exception {
		initDir = Outside.service(this,"gus.b.entityclass1.jardir.init");
		dirToFiles = Outside.service(this,"gus.a.dir.jar.files");
		appLocation = Outside.service(this,"gus.a.app.location");
		jarDir = (File) Outside.resource(this,"path#path.jardir");
	}
	
	
	public Object g() throws Exception {
		if(classpath==null) init();
		return classpath;
	}
	
	
	private void init() throws Exception {
		initDir.e();
		File[] jars = (File[]) dirToFiles.t(jarDir);
		File location = (File) appLocation.g();
		
		classpath = new File[jars.length+1];
		classpath[0] = location;
		for(int i=0;i<jars.length;i++)
			classpath[i+1] = jars[i];
	}
}
