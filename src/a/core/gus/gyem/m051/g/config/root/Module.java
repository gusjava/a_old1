package a.core.gus.gyem.m051.g.config.root;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		String configId = (String) moduleG(M009_G_CONFIG_ID).g();
		return moduleT(M052_T_CONFIGID_TO_ROOT).t(configId);
	}
}
