package a.entity.gus.b.entityclass1.cl.classpath.view1;

import java.net.URLClassLoader;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210827";}

	
	private Service findClassLoader;
	private Service clToFiles;
	private Service viewer;

	public EntityImpl() throws Exception {
		findClassLoader = Outside.service(this,"gus.b.entityclass1.cl.find");
		clToFiles = Outside.service(this,"gus.a.classloader.urls.as.filearray");
		viewer = Outside.service(this,"*gus.b.filearray1.view1");
		
		URLClassLoader cl = (URLClassLoader) findClassLoader.g();
		Object data = clToFiles.t(cl);
		viewer.p(data);
	}
	
	public Object i() throws Exception {
		return viewer.i();
	}
}
