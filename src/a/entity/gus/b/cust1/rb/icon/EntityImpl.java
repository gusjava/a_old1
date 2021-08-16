package a.entity.gus.b.cust1.rb.icon;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210816";}

	
	private Service iconProvider;

	public EntityImpl() throws Exception {
		iconProvider = Outside.service(this,"gus.b.icons1.provider");
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String info = (String) data[1];
		return iconProvider.t(info);
	}
}
