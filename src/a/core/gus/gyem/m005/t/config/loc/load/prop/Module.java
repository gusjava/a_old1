package a.core.gus.gyem.m005.t.config.loc.load.prop;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String configLoc = (String) obj;
		String configPath = (String) moduleT(M008_T_CONFIG_LOC_TO_PATH).t(configLoc);
		return moduleT(M006_T_CONFIG_PATH_LOAD_PROP).t(configPath);
	}
}
