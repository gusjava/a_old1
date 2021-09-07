package a.entity.gus.a.dir.listing0.files.java;

import java.io.File;
import java.io.FileFilter;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210907";}
	
	public static final String EXT = "java";
	
	
	public Object t(Object obj) throws Exception {
		File dir = (File) obj;
		if(!dir.isDirectory()) return null;
		
		return dir.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.isFile() && f.getName().endsWith("."+EXT);
			}
		});
	}
}
