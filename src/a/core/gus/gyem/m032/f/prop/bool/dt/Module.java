package a.core.gus.gyem.m032.f.prop.bool.dt;

import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.F;

public class Module extends GyemSystem implements F {
	public static final boolean DEFAULT_VALUE = true;

	public boolean f(Object obj) throws Exception {
		String key = (String) obj;
		Map prop = (Map) moduleG(M003_G_PROP).g();
		if(!prop.containsKey(key)) return DEFAULT_VALUE;
		
		String value = (String) prop.get(key);
		return value.toLowerCase().equals("true");
	}
}
