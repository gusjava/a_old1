package a.entity.gus.a.features.i.exception;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	
	public String creationDate() {
		return "20210805";
	}

	public Object i() throws Exception {
		throw new Exception("Broouhaaacrk");
	}
}
