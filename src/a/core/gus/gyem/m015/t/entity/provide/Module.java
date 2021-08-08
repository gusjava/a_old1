package a.core.gus.gyem.m015.t.entity.provide;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String rule = (String) obj;
		
		if(rule.startsWith("*")) return moduleT(M017_T_ENTITY_GENERATE).t(rule.substring(1));
		if(rule.startsWith("+")) return moduleT(M016_T_ENTITY_UNIQUE).t(rule.substring(1));
		return moduleT(M016_T_ENTITY_UNIQUE).t(rule);
	}
}
