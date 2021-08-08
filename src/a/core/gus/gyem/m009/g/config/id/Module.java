package a.core.gus.gyem.m009.g.config.id;

import java.util.Map;
import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		Map param = (Map) moduleG(M010_G_PARAM).g();
		return param.containsKey(PARAM_CONFIG) ? param.get(PARAM_CONFIG) : null;
	}
}
