package a.entity.gus.b.cust1.rb.g;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210810";}

	
	private Service provider;

	public EntityImpl() throws Exception {
		provider = Outside.service(this,"m040.t.resource.provide");
	}
	
	
	public Object t(Object obj) throws Exception {
		G g = (G) provider.t(obj);
		if(g==null) throw new Exception("Enable to call g on null");
		return g.g();
	}
}
