package a.core.gus.gyem.m018.t.entity.findclass;

import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;
import a.framework.T;

public class Module extends GyemSystem implements T, G {
	
	private Map map = new HashMap();
	
	public Object t(Object obj) throws Exception {
		String entityName = (String) obj;
		
		if(!map.containsKey(entityName))
			map.put(entityName, moduleT(M019_T_ENTITY_LOADCLASS).t(entityName));
		return map.get(entityName);
	}
	
	public Object g() throws Exception {
		return map;
	}
}
