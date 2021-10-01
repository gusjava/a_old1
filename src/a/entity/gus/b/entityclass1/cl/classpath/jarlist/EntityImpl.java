package a.entity.gus.b.entityclass1.cl.classpath.jarlist;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210820";}


	private Service dirToFiles;
	private Service findJreClassPath;
	private Service getConfig;
	private Service extractJar;
	
	private File jarDir;

	public EntityImpl() throws Exception {
		dirToFiles = Outside.service(this,"gus.a.dir.jar.files");
		findJreClassPath = Outside.service(this,"gus.a.jre.prop.classpath.as.filearray");
		getConfig = Outside.service(this,"gus.b.entityclass1.cl.classpath.jarlist.config");
		extractJar = Outside.service(this,"gus.b.entityclass1.cl.classpath.jarlist.extract");
		
		jarDir = (File) Outside.resource(this,"path#path.jardir");
	}
	
	
	public Object g() throws Exception
	{
		List list = new ArrayList();

		File[] localJars = (File[]) dirToFiles.t(jarDir);
		File[] jreClasspath = (File[]) findJreClassPath.g();
		
		Map mapLocalJars = toMap(localJars);
		Map mapJreClasspath = toMap(jreClasspath);
		
		String[] nn = (String[]) getConfig.g();
		for(String n : nn) if(!mapJreClasspath.containsKey(n)) 
		{
			if(mapLocalJars.containsKey(n)) 
			{
				list.add(mapLocalJars.get(n));
			}
			else
			{
				File newJar = new File(jarDir,n);
				extractJar.p(newJar);
				if(!newJar.isFile()) throw new Exception("Failed to init jar: "+newJar);
				list.add(newJar);
			}
		}
		return list;
	}
	
	
	private Map toMap(File[] ff) {
		Map map = new HashMap();
		if(ff!=null) for(File f : ff) map.put(f.getName(), f);
		return map;
	}
}
