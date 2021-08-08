package a.core.gus.gyem.m044.t.entity.findname;

import a.core.gus.gyem.GyemSystem;
import a.framework.Entity;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		if(obj==null) return null;
		
		String className = obj.getClass().getName();
		
		if(!(obj instanceof Entity))
			throw new Exception("Invalid entity class: "+className);
		
		if(className.length()<=ENTITYCLASS_START.length() + ENTITYCLASS_END.length())
			throw new Exception("Invalid entity class: "+className);
		
		if(!className.startsWith(ENTITYCLASS_START))
			throw new Exception("Invalid entity class: "+className);
		
		if(!className.endsWith(ENTITYCLASS_END))
			throw new Exception("Invalid entity class: "+className);
		
		return className.substring(ENTITYCLASS_START.length(), className.length() - ENTITYCLASS_END.length());
	}
}
