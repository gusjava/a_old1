package a.entity.gus.b.appli1.main.cust;

import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20210816";}

	
	private Service custIcon;
	private Map main;

	public EntityImpl() throws Exception {
		custIcon = Outside.service(this,"gus.b.cust1.rb.icon");
		main = (Map) Outside.resource(this,"main");
	}
	
	public void e() throws Exception {
		main.put("rb.icon",custIcon);
	}
}
