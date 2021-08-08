package a.core.gus.gyem.m034.t.config.path.url;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String configPath = (String) obj;
		return getClass().getResource(configPath);
	}
}
