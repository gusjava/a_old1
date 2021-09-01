package a.entity.gus.b.entitysrc2.listing.lastmodified;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}

	public static final String FILENAME = "EntityImpl.java";

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = new HashMap();
		
		dir = new File(new File(dir,"a"),"entity");
		if(!dir.isDirectory()) return map;
		
		int rootLength = dir.getAbsolutePath().length();
		scan(dir,rootLength,map);
		
		return map;
	}
	
	
	private void scan(File path, int rootLength, Map map)
	{
		if(isEntityFile(path))
		{
			String entityName = toEntityName(path, rootLength);
			long lastModified = path.lastModified();
			map.put(entityName, lastModified);
		}
		else if(path.isDirectory())
		{
			File[] ff = path.listFiles();
			for(File f:ff) scan(f,rootLength,map);
		}
	}
	
	
	
	private boolean isEntityFile(File f)
	{return f.isFile() && f.getName().equals(FILENAME);}
	
	
	
	private String toEntityName(File f, int rootLength)
	{
		String p = f.getParent();
		return p.substring(rootLength+1).replace(File.separator,".");
	}
}
