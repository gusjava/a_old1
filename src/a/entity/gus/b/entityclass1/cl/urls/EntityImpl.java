package a.entity.gus.b.entityclass1.cl.urls;

import java.io.File;
import java.net.URL;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210820";}

	
	private Service initDir;
	private Service dirToUrls;
	private File jarDir;
	
	private URL[] urls;

	public EntityImpl() throws Exception {
		initDir = Outside.service(this,"gus.b.entityclass1.jardir.init");
		dirToUrls = Outside.service(this,"gus.a.dir.jar.urls");
		jarDir = (File) Outside.resource(this,"path#path.jardir");
	}
	
	
	public Object g() throws Exception {
		if(urls==null) init();
		return urls;
	}
	
	private void init() throws Exception {
		initDir.e();
		urls = (URL[]) dirToUrls.t(jarDir);
	}
}
