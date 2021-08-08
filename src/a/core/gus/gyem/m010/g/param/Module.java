package a.core.gus.gyem.m010.g.param;

import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private Map param;
	
	public Object g() throws Exception {
		if(param==null) init();
		return param;
	}
	
	private void init() throws Exception {
		param = new HashMap();
		
		Map inside = (Map) moduleG(M012_G_PARAM_INSIDE).g();
		if(inside!=null) param.putAll(inside);
		
		Map outside = (Map) moduleG(M011_G_PARAM_OUTSIDE).g();
		if(outside!=null) param.putAll(outside);
	}
}
