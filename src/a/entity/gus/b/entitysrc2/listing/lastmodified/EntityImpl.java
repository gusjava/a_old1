package a.entity.gus.b.entitysrc2.listing.lastmodified;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}

	public static final String FILENAME = "EntityImpl.java";

	public static final FileFilter FILTER_DIR = new FileFilter() {
		public boolean accept(File f) {return f.isDirectory();}
	};
	
	public static final FileFilter FILTER_JAVA = new FileFilter() {
		public boolean accept(File f) {return f.isFile() && f.getName().endsWith(".java");}
	};
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = new HashMap();
		
		dir = new File(new File(dir,"a"),"entity");
		if(!dir.isDirectory()) return map;
		
		int rootLength = dir.getAbsolutePath().length();
		scanDir(dir, rootLength, map);
		
		return map;
	}
	

	private void scanDir(File dir, int rootLength, Map map)
	{
		File entityFile = new File(dir, FILENAME);
		if(entityFile.isFile()) scanEntityDir(dir, rootLength, map);
		
		File[] dd = dir.listFiles(FILTER_DIR);
		for(File d:dd) scanDir(d, rootLength, map);
	}
	
	
	private void scanEntityDir(File dir, int rootLength, Map map)
	{
		File[] ff = dir.listFiles(FILTER_JAVA);
		long lastModified = 0;
		for(File f:ff) {
			long lm = f.lastModified();
			if(lastModified < lm) lastModified = lm;
		}
		String entityName = toEntityName(dir, rootLength);
		map.put(entityName, lastModified);
	}
	
	
	private String toEntityName(File dir, int rootLength)
	{return dir.getAbsolutePath().substring(rootLength+1).replace(File.separator,".");}
}
