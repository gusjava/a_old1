package a.core.gus.gyem.m001.e.customize;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		Map prop = (Map) moduleG(M003_G_PROP).g();
		Map cust = new HashMap();
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.startsWith("cust.")) {
				String name = key.substring(5);
				Object module = moduleT(M013_T_MODULE_BUILD).t(prop.get(key));
				cust.put(name, module);
			}
		}
		main.putAll(cust);
	}
}
