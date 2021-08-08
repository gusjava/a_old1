package a.core.gus.gyem.m039.t.resource.findrule1;

import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.Entity;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;

		Map mapping = (Map) data[0];
		String entityName = (String) data[1];
		String id = (String) data[2];
		
		if(entityName==null) {
			if(mapping.containsKey("@"+id))
				return mapping.get("@"+id);
			return id;
		}
		
		String pseudo = entityName.split("\\.")[0];
		
		if(mapping.containsKey(entityName+"@"+id))
			return mapping.get(entityName+"@"+id);
			
		if(mapping.containsKey(pseudo+"@"+id))
			return mapping.get(pseudo+"@"+id);
			
		if(mapping.containsKey("@"+id))
			return mapping.get("@"+id);
			
		return id;
	}
}
