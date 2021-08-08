package a.core.gus.gyem.m042.t.resource.find.rb;

import java.util.Iterator;

import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.utils.UtilJava;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String id = (String) obj;
		if(UtilJava.isKeyword(id)) id += "1";

		Iterator it = main.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.equals("rb."+id) || key.endsWith(".rb."+id)) return get(key);
		}
		throw new Exception("RB not found inside main for id ["+id+"]");
	}
}
