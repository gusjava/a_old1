package a.entity.gus.b.entityclass1.cl.classpath;

import java.io.File;
import java.util.List;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210820";}

	
	private Service getJarList;
	private Service appLocation;
	
	private File[] classpath;

	public EntityImpl() throws Exception {
		getJarList = Outside.service(this,"gus.b.entityclass1.cl.classpath.jarlist");
		appLocation = Outside.service(this,"gus.a.app.location");
	}
	
	
	public Object g() throws Exception {
		if(classpath==null) init();
		return classpath;
	}
	
	
	private void init() throws Exception {
		List jarList = (List) getJarList.g();
		int nb = jarList.size();
		
		classpath = new File[nb+1];
		classpath[0] = (File) appLocation.g();
		for(int i=0;i<nb;i++)
			classpath[i+1] = (File) jarList.get(i);
	}
}
