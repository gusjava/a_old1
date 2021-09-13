package a.entity.gus.b.entitysrc1.generator1;

import java.io.File;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210806";}

	private Service generate;
	private Service validate;
	private File rootDir;
	private String devId;
	
	public EntityImpl() throws Exception {
		generate = Outside.service(this,"gus.a.entity.src.generate1");
		validate = Outside.service(this,"gus.a.entity.name.validate");
		rootDir = (File) Outside.service(this, "gus.b.entitysrc1.rootdir").g();
		devId = (String) Outside.resource(this, "param#dev");
		
		if(devId==null) throw new Exception("dev not found inside params");
	}
	
	public void p(Object obj) throws Exception {
		String rule = (String) obj;
		
		String[] nn = rule.split(" ", 2);
		String entityName = nn[0];
		String features = nn.length>1 ? nn[1] : "";
		
		if(!entityName.startsWith(devId+"."))
			entityName = devId+"."+entityName;
		
		if(!validate.f(entityName)) throw new Exception("Invalid entity name: "+entityName);
		generate.p(new Object[] {rootDir, entityName, features});
	}
}
