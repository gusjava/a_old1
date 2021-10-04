package a.entity.gus.a.framework.src.find.packagedir;

import java.io.File;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20211003";}
	
	public Object t(Object obj) throws Exception
	{
		File rootDir = (File) obj;
		return new File(new File(rootDir,"a"),"framework");
	}
}
