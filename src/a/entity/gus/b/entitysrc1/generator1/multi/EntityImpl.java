package a.entity.gus.b.entitysrc1.generator1.multi;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210813";}

	
	private Service generator;

	public EntityImpl() throws Exception {
		generator = Outside.service(this, "gus.b.entitysrc1.generator1");
	}
	
	public void p(Object obj) throws Exception {
		String[] lines = ((String) obj).split("\n");
		
		for(String line : lines) {
			generator.p(line);
		}
	}
}
