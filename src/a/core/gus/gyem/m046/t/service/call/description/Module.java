package a.core.gus.gyem.m046.t.service.call.description;

import a.core.gus.gyem.GyemSystem;
import a.framework.Entity;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] call = (Object[]) obj;
		
		Entity entity = (Entity) call[0];
		String id =  (String) call[1];
		
		String entityName = (String) moduleT(M044_T_ENTITY_FINDNAME).t(entity);
		return entityName+"@"+id;
	}
}
