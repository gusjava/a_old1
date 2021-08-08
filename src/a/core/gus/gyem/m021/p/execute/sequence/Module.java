package a.core.gus.gyem.m021.p.execute.sequence;

import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.P;

public class Module extends GyemSystem implements P {
	
	public void p(Object obj) throws Exception {
		String key = (String) obj;
		
		if(perform(key)) return;
		int index = 0;
		while(perform(key+"."+index)) index++;
	}
	
	private boolean perform(String key) throws Exception {
		try {
			Map prop = (Map) moduleG(M003_G_PROP).g();
			if(!prop.containsKey(key)) return false;
			
			String rule = (String) prop.get(key);
			return moduleF(M022_F_EXECUTE_RULE).f(rule);
		}
		catch(Exception e) {
			String message = "Failed to handle key: ["+key+"]";
			throw new Exception(message, e);
		}
	}
}
