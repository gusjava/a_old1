package a.core.gus.gyem.m001.e.customize;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		if(perform("cust")) return;
		int index = 0;
		while(perform("cust"+index)) index++;
	}
	
	private boolean perform(String head) throws Exception {
		Map prop = (Map) moduleG(M003_G_PROP).g();
		Map cust = new HashMap();
		int len = head.length()+1;
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.startsWith(head+".")) {
				String name = key.substring(len);
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
		
		if(cust.isEmpty()) return false;
		main.putAll(cust);
		return true;
	}
}
