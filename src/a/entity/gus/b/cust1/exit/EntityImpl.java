package a.entity.gus.b.cust1.exit;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E {
	public String creationDate() {return "20210814";}
	
	
	public void e() throws Exception {
		send(this,"exit");
		System.exit(0);
	}
}
