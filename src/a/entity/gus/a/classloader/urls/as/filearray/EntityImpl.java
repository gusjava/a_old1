package a.entity.gus.a.classloader.urls.as.filearray;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210827";}
	
	
	public Object t(Object obj) throws Exception {
		URLClassLoader cl = (URLClassLoader) obj;
		URL[] urls = cl.getURLs();
		File[] files = new File[urls.length];
		for(int i=0;i<urls.length;i++) {
			String path = urls[i].toString();
			if(!path.startsWith("file:/")) throw new Exception("Invalid URL: "+path);
			files[i] = new File(path.substring(6));
		}
		return files;
	}
}
