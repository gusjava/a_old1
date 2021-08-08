package a.core.gus.gyem.m040.t.resource.provide;

import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.utils.UtilString;
import a.framework.T;

public class Module extends GyemSystem implements T {

	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;

		Object[] call = (Object[]) data[0];
		String rule = (String) data[1];
		
		try {
			String rule1 = formatRule(rule);
			
			if(rule1.equals("null")) return null;
			if(rule1.equals("main")) return main;
			if(has(rule1)) return get(rule1);
	
			return moduleT(M041_T_RESOURCE_BUILD).t(new Object[]{call, rule1});
		}
		catch(Exception e) {
			String message = "Resource could not be provided with rule: "+rule;
			throw new Exception(message, e);
		}
	}

	private String formatRule(String s) {
		if(!s.contains("-")) return s;
		String[] n = s.split("-", 2);
		if(!UtilString.isInteger(n[1])) return s;
		return n[0];
	}
}
