package a.entity.gus.a.dir.jar.urls;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210820";}
	
	
	public Object t(Object obj) throws Exception {
		File dir = (File) obj;
		if(!dir.isDirectory()) return new URL[0];
		
		File[] jars = dir.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.isFile() && f.getName().endsWith(".jar");
			}
		});
		
		URL[] url = new URL[jars.length];
		for(int i=0;i<url.length;i++)
			url[i] = jars[i].toURI().toURL();
		
		return url;
	}
}
