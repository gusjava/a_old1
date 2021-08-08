package a.core.gus.gyem.m013.t.module.build;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {

	public Object t(Object obj) throws Exception {
		String rule = (String) obj;
		
		if(rule.equals("null")) return moduleG(M014_G_SERVICE_BUILD_EMPTY).g();
		return moduleT(M015_T_ENTITY_PROVIDE).t(rule);
	}
}
