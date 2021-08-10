package a.entity.gus.b.cust1.rb.prop;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210809";}


	private Map prop;

	public EntityImpl() throws Exception {
		prop = (Map) Outside.service(this,"m003.g.prop").g();
	}

	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String info = (String) data[1];
		return prop.containsKey(info) ? prop.get(info) : null;
	}
}
