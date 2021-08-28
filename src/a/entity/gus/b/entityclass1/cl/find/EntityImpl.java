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

	
	private Service findClasspath;
	
	private ClassLoader cl;

	public EntityImpl() throws Exception {
		findClasspath = Outside.service(this,"gus.b.entityclass1.cl.classpath");
	}
	
	public Object g() throws Exception {
		if(cl==null) init();
		return cl;
	}
	
	private void init() throws Exception {
		File[] classpath = (File[]) findClasspath.g();
		
		URL[] urls = new URL[classpath.length];
		for(int i=0;i<classpath.length;i++)
			urls[i] = classpath[i].toURI().toURL();
		
		cl = new EntityClassLoader(urls);
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
