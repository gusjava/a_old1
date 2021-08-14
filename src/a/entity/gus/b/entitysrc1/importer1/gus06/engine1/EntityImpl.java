package a.entity.gus.b.entitysrc1.importer1.gus06.engine1;

import java.io.File;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210814";}

	
	private Service engine;
	private File outputRoot;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.b.entitysrc1.importer1.gus06.engine2");
		outputRoot = (File) Outside.service(this, "gus.b.entitysrc1.rootdir").g();
	}
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		
		File inputRoot = (File) o[0];
		String input = (String) o[1];
		
		String[] lines = input.split("\n");
		
		String line0 = null;
		for(String line : lines) if(!line.trim().equals("")) {
			if(line0==null) line0 = line;
			else {
				engine.p(new Object[] {inputRoot, line0, outputRoot, line});
				line0 = null;
			}
		}
	}
}
