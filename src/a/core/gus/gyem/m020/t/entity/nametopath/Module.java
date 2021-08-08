package a.core.gus.gyem.m020.t.entity.nametopath;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String entityName = (String) obj;
		return ENTITYCLASS_START + entityName + ENTITYCLASS_END;
	}
}
