package a.entity.gus.a.dir.jar.files;

import java.io.File;
import java.io.FileFilter;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210827";}
	
	
	public Object t(Object obj) throws Exception {
		File dir = (File) obj;
		if(!dir.isDirectory()) return new File[0];
		
		File[] jars = dir.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.isFile() && f.getName().endsWith(".jar");
			}
		});
		
		return jars;
	}
}
