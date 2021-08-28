package a.core.gus.gyem.m016.t.entity.unique;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;
import a.framework.G;
import a.framework.T;

public class Module extends GyemSystem implements T, G, E {
	
	private Map map = new ConcurrentHashMap();
	
	public Object t(Object obj) throws Exception {
		String entityName = (String) obj;
		
		if(!map.containsKey(entityName))
			map.put(entityName, moduleT(M017_T_ENTITY_GENERATE).t(entityName));
		return map.get(entityName);
	}
	
	public Object g() throws Exception {
		return map;
	}

	public void e() throws Exception {
		map.clear();
	}
}
