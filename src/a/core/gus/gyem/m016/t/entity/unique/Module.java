package a.core.gus.gyem.m016.t.entity.unique;

import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	private Map map = new HashMap();
	
	public Object t(Object obj) throws Exception {
		String entityName = (String) obj;
		
		if(!map.containsKey(entityName))
			map.put(entityName, moduleT(M017_T_ENTITY_GENERATE).t(entityName));
		return map.get(entityName);
	}
}
