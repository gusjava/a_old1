package a.core.gus.gyem.m041.t.resource.build;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {

	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;

		Object[] call = (Object[]) data[0];
		String rule = (String) data[1];
		
		String[] n = analyzeRule(rule);
		String id = n[0];
		String info = n[1];
		
		T rb = (T) moduleT(M042_T_RESOURCE_FIND_RB).t(id);
		return rb.t(new Object[]{call, info});
	}
	
	private String[] analyzeRule(String rule) {
		if(rule.contains("#")) return rule.split("#",2);
		return new String[]{RB_DEFAULT, rule};
	}
}
