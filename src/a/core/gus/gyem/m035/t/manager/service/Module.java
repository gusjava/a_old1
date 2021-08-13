package a.core.gus.gyem.m035.t.manager.service;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] call = (Object[]) obj;
		Object result = moduleT(M036_T_MANAGER_RESOURCE).t(call);
		return moduleT(M046_T_SERVICE_WRAP).t(new Object[] {call, result});
	}
}
