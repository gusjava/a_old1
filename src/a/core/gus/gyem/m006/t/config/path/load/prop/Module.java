package a.core.gus.gyem.m006.t.config.path.load.prop;

import java.io.InputStream;
import java.util.Properties;
import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String configPath = (String) obj;
		InputStream is = (InputStream) moduleT(M007_T_CONFIG_PATH_INPUTSTREAM).t(configPath);
		if(is==null) return null;
		
		Properties prop = new Properties();
		prop.load(is);
		is.close();
		
		return prop;
	}
}
