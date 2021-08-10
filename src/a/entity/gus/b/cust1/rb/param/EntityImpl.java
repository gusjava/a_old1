package a.entity.gus.b.cust1.rb.param;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210809";}

	private Map params;

	public EntityImpl() throws Exception {
		params = (Map) Outside.service(this,"m010.g.param").g();
	}

	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String info = (String) data[1];
		return params.containsKey(info) ? params.get(info) : null;
	}
}
