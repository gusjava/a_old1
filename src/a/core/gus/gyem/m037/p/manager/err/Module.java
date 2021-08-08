package a.core.gus.gyem.m037.p.manager.err;

import java.util.Date;
import java.util.List;

import a.core.gus.gyem.GyemSystem;
import a.framework.Entity;
import a.framework.P;

public class Module extends GyemSystem implements P {
	
	public void p(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		
		Entity entity = (Entity) data[0];
		String id =  (String) data[1];
		Exception e = (Exception) data[2];
		
		Date date = new Date();
		
		List list = (List) moduleG(M049_G_ERR_LIST).g();
		list.add(new Object[] {entity, id, date, e});
		
		e.printStackTrace();
	}
}
