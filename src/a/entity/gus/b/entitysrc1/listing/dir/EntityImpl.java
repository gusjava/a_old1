package a.entity.gus.b.entitysrc1.listing.dir;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210828";}

	public static final String FILENAME = "EntityImpl.java";
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		List list = new ArrayList();
		
		dir = new File(new File(dir,"a"),"entity");
		if(!dir.isDirectory()) return list;
		
		int rootLength = dir.getAbsolutePath().length();
		scan(dir,rootLength,list);
		
		Collections.sort(list);
		return list;
	}
	
	
	
	private void scan(File path, int rootLength, List list)
	{
		if(isEntityFile(path))
			list.add(toEntityName(path, rootLength));
		
		else if(path.isDirectory())
		{
			File[] ff = path.listFiles();
			for(File f:ff) scan(f,rootLength,list);
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
