package a.entity.gus.b.cust1.rb.path;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}

	private Service provider;

	public EntityImpl() throws Exception {
		provider = Outside.service(this, "gus.b.paths1.provider.main");
	}
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String info = (String) data[1];

		return provider.r(info);
	}
}
