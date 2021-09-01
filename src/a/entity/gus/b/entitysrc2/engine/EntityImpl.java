package a.entity.gus.b.entitysrc2.engine;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210830";}

	
	private Service getListing;
	private Service retrieveData;
	private Service analyzeEntity;

	public EntityImpl() throws Exception {
		getListing = Outside.service(this,"gus.b.entitysrc2.listing.lastmodified");
		retrieveData = Outside.service(this,"gus.b.entitysrc2.database.entity.findall.asmap");
		analyzeEntity = Outside.service(this,"gus.b.entitysrc2.analyze.entity");
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = (File) o[0];
		Connection cx = (Connection) o[1];
		Long lastTime = (Long) o[2];
		
		
		Map listing = (Map) getListing.t(rootDir);
		Map data = (Map) retrieveData.t(cx);
		if(data==null) throw new Exception("null data retrieved from cx");
		
		Map results = new HashMap();
		
		Iterator it = listing.keySet().iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			long lastModified = (long) listing.get(entityName);
			boolean outDated = lastModified > lastTime;
			
			Map result = findResult(entityName, data, outDated, rootDir, cx);
			results.put(entityName, result);
		}
		return results;
	}
	
	
	private Map findResult(String entityName, Map data, boolean outDated, File rootDir, Connection cx)  throws Exception {
		if(!outDated && data.containsKey(entityName)) 
			return (Map) data.get(entityName);
		return (Map) analyzeEntity.t(new Object[] {entityName, rootDir, cx});
	}
}
