package a.core.gus.gyem.m017.t.entity.generate;

import a.core.gus.gyem.GyemSystem;
import a.framework.Entity;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String entityName = (String) obj;
		Class c = (Class) moduleT(M018_T_ENTITY_FINDCLASS).t(entityName);
		
		Entity entity = null;
		try {
			entity = (Entity) c.newInstance();
			
			String date = entity.creationDate();
			if(date==null) throw new Exception("Invalid entity creationDate null value");
			if(!date.matches("[0-9]{8}")) throw new Exception("Invalid entity creationDate value format: "+date);
		}
		catch(Exception e) {
			String message = "Failed to instanciate entity: "+entityName;
			throw new Exception(message,e);
		}
		return entity;
	}
}
