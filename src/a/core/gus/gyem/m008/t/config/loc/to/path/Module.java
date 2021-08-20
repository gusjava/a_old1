package a.core.gus.gyem.m008.t.config.loc.to.path;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String configLoc = (String) obj;
		if(configLoc==null) return null;
		
		String configRoot = (String) moduleG(M051_G_CONFIG_ROOT).g();
		return configRoot + configLoc;
	}
}
