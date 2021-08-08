package a.entity.gus.first;

import a.framework.E;
import a.framework.Entity;

public class EntityImpl implements Entity, E {
	
	public String creationDate() {
		return "20210805";
	}

	public void e() throws Exception {
		System.out.println("Ma première entité est rigolote");
	}
}
