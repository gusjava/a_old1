package a.entity.gus.b.entityclass1.cl.find;

import java.net.URL;
import java.net.URLClassLoader;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210820";}

	
	private Service findUrls;
	
	private ClassLoader cl;

	public EntityImpl() throws Exception {
		findUrls = Outside.service(this,"gus.b.entityclass1.cl.urls");
	}
	
	public Object g() throws Exception {
		if(cl==null) init();
		return cl;
	}
	
	private void init() throws Exception {
		URL[] urls = (URL[]) findUrls.g();
		cl = new URLClassLoader(urls);
	}
}
