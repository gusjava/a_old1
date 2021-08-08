package a.core.gus.gyem.m028.g.buildgui.entity;

import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;
import a.framework.I;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		Map prop = (Map) moduleG(M003_G_PROP).g();
		if(!prop.containsKey(PROP_APP_MAINGUI)) return null;
		
		String rule = (String) prop.get(PROP_APP_MAINGUI);
		Object entity = moduleT(M015_T_ENTITY_PROVIDE).t(rule);
		return ((I) entity).i();
	}
}
