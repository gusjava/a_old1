package a.core.gus.gyem.m003.g.prop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private Map prop;

	public Object g() throws Exception {
		if(prop==null) init();
		return prop;
	}
	
	private void init() throws Exception {
		prop = new HashMap();
		
		Map inside = (Map) moduleG(M004_G_PROP_CONFIG).g();
		if(inside!=null) prop.putAll(inside);
		
		Map param = (Map) moduleG(M010_G_PARAM).g();
		Iterator it = param.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.startsWith("p.")) {
				String value = (String) param.get(key);
				prop.put(key.substring(2), value);
			}
		}
	}
}
