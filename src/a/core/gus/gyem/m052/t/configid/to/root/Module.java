package a.core.gus.gyem.m052.t.configid.to.root;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String configId = (String) obj;
		return configId!=null ? CONFIGPATH_ROOT + configId.replace(".","/") + "/" : CONFIGPATH_ROOT;
	}
}
