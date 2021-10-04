package a.entity.gus.a.entity.bin.find.entityfile;

import java.io.File;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20211003";}

	public static final String FILENAME = "EntityImpl.class";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		String entityName = (String) o[1];
		
		String path = ("a.entity."+entityName).replace(".", File.separator);
		return new File(new File(dir, path), FILENAME);
	}
}
