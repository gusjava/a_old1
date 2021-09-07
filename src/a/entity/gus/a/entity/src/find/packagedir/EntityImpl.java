package a.entity.gus.a.entity.src.find.packagedir;

import java.io.File;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210907";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		File rootDir = (File) o[0];
		String entityName = (String) o[1];
		
		File dir = new File(new File(rootDir,"a"),"entity");
		return new File(dir, entityName.replace(".",File.separator));
	}
}
