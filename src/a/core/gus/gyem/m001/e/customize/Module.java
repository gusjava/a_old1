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
				String value = (String) prop.get(key);
				
				try {
					Object module = moduleT(M013_T_MODULE_BUILD).t(value);
					cust.put(name, module);
				}
				catch(Exception e) {
					String message = "Failed to customize main with prop key="+key+" & value="+value;
					throw new Exception(message, e);
				}
			}
		}
		main.putAll(cust);
	}
}
