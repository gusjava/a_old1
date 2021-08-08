package a.core.gus.gyem.m019.t.entity.loadclass;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String entityName = (String) obj;
		String classPath = (String) moduleT(M020_T_ENTITY_NAMETOPATH).t(entityName);
		return Class.forName(classPath);
	}
}
