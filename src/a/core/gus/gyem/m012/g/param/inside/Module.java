package a.core.gus.gyem.m012.g.param.inside;

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
		param = (Map) moduleT(M006_T_CONFIG_PATH_LOAD_PROP).t(CONFIGPATH_PARAM);
	}
}
