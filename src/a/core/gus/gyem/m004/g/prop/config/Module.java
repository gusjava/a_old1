package a.core.gus.gyem.m004.g.prop.config;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		return moduleT(M005_T_CONFIG_LOC_LOAD_PROP).t(CONFIGLOC_PROP);
	}
}
