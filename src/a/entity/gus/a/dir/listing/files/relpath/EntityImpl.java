package a.entity.gus.a.dir.listing.files.relpath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		int len = dir.getAbsolutePath().length()+1;

		List entries = new ArrayList();
		handleDir(entries, dir, len);
		return entries;
	}
	
	
	private void handleDir(List list, File dir, int len)
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			if(f.isDirectory()) handleDir(list,f,len);
			else list.add(f.getAbsolutePath().substring(len));
		}
	}
}
