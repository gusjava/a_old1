package a.entity.gus.b.cust1.rb.pool;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}

	private Service findName;
	
	private File storeDir;

	public EntityImpl() throws Exception {
		findName = Outside.service(this,"m044.t.entity.findname");
		
		storeDir = (File) Outside.service(this,"gus.b.paths1.dir.pooldir").g();
		if(storeDir==null) throw new Exception("Pool dir could not be found");
		storeDir.mkdirs();
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		Object[] call = (Object[]) data[0];
		
		Entity entity = (Entity) call[0];
		String entityName = (String) findName.t(entity);
		
		File entityDir = new File(storeDir, entityName);
		entityDir.mkdirs();
		if(!entityDir.isDirectory())
			throw new Exception("Failed to initialize entity pool dir: "+entityDir);
		return entityDir;
	}
}
