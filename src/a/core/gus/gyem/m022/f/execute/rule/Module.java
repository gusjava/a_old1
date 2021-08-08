package a.core.gus.gyem.m022.f.execute.rule;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;
import a.framework.F;

public class Module extends GyemSystem implements F {
	
	public boolean f(Object obj) throws Exception {
		String rule = (String) obj;
		
		if(rule.equals("null")) return true;
		if(rule.startsWith("!")) return true;
		if(rule.startsWith("#")) return false;
		
		if(rule.startsWith("e:")) return execute(rule.substring(2));
		if(rule.startsWith("r:")) return run(rule.substring(2));
		
		return provide(rule);
	}
	
	
	
	private boolean execute(String rule) throws Exception {
		E exe = (E) moduleT(M015_T_ENTITY_PROVIDE).t(rule);
		exe.e();
		return true;
	}
	
	private boolean run(String rule) throws Exception {
		Runnable r = (Runnable) moduleT(M015_T_ENTITY_PROVIDE).t(rule);
		r.run();
		return true;
	}
	
	private boolean provide(String rule) throws Exception {
		moduleT(M015_T_ENTITY_PROVIDE).t(rule);
		return true;
	}
}
