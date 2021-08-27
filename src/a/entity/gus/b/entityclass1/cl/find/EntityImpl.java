package a.entity.gus.b.entityclass1.cl.find;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210820";}

	
	private Service findUrls;
	private Service appLocation;
	
	private ClassLoader cl;

	public EntityImpl() throws Exception {
		findUrls = Outside.service(this,"gus.b.entityclass1.cl.urls");
		appLocation = Outside.service(this,"gus.a.app.location");
	}
	
	public Object g() throws Exception {
		if(cl==null) init();
		return cl;
	}
	
	private void init() throws Exception {
		URL[] urls = (URL[]) findUrls.g();
		File location = (File) appLocation.g();
		
		URL[] urls1 = new URL[urls.length+1];
		urls1[0] = location.toURI().toURL();
		for(int i=0;i<urls.length;i++)
			urls1[i+1] = urls[i];
		
		cl = new EntityClassLoader(urls1);
	}
	
	
	
	private class EntityClassLoader extends URLClassLoader
	{
		public EntityClassLoader(URL[] urls)
		{super(urls);}
	
		protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException
		{
			if(!name.startsWith("a.entity."))
				return super.loadClass(name, resolve);
			
			synchronized(getClassLoadingLock(name))
			{
				Class c = findLoadedClass(name);
				if(c!=null) return c;
			
				c = findClass(name);
				if(resolve) resolveClass(c);
				if(c!=null) return c;
			}
			return super.loadClass(name, resolve);
		}
	}
}
