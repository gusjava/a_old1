package a.entity.gus.a.features.e.exception.random;

import a.framework.E;
import a.framework.Entity;

public class EntityImpl implements Entity, E {
	
	public String creationDate() {
		return "20210826";
	}

	public void e() throws Exception {
		int number = (int) (Math.random()*100);
		throw new Exception("Random number: "+number);
	}
}
