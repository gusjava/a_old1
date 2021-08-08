package a.core.gus.gyem.m036.t.manager.resource;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] call = (Object[]) obj;
		String rule = (String) moduleT(M038_T_RESOURCE_FINDRULE).t(call);
		return moduleT(M040_T_RESOURCE_PROVIDE).t(new Object[] {call, rule});
	}
}
