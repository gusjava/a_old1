package a.core.gus.gyem.m043.t.rb.entity;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;

		String info = (String) data[1];
		return moduleT(M015_T_ENTITY_PROVIDE).t(info);
	}
}
