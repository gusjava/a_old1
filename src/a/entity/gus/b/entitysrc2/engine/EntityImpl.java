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


	private Service analyzeEntity;
	private Service getListing;
	private Service findAll;
	private Service insertEntity;
	private Service updateEntity;
	private Service insertServices;
	private Service deleteServices;
	private Service insertResources;
	private Service deleteResources;

	
	public EntityImpl() throws Exception {
		analyzeEntity = Outside.service(this,"gus.b.entitysrc2.analyze.entity");
		getListing = Outside.service(this,"gus.b.entitysrc2.listing.lastmodified");
		findAll = Outside.service(this,"gus.b.entitysrc2.database.entity.findall.asmap");
		insertEntity = Outside.service(this,"gus.b.entitysrc2.database.entity.insert");
		updateEntity = Outside.service(this,"gus.b.entitysrc2.database.entity.update");
		insertServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.insert");
		deleteServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.delete");
		insertResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.insert");
		deleteResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.delete");
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = (File) o[0];
		Connection cx = (Connection) o[1];
		Long lastTime = (Long) o[2];
		
		
		Map listing = (Map) getListing.t(rootDir);
		Map dbMap = (Map) findAll.t(cx);
		if(dbMap==null) throw new Exception("null data retrieved from cx");
		
		Map results = new HashMap();
		
		int foundNb = 0;
		int outDatedNb = 0;
		int analyzedNb = 0;
		
		Iterator it = listing.keySet().iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			long lastModified = (long) listing.get(entityName);
			
			boolean outDated = lastModified > lastTime;
			boolean dbFound = dbMap.containsKey(entityName);
			
			if(dbFound) foundNb++;
			if(outDated) outDatedNb++;
			if(outDated || !dbFound) analyzedNb++;
			
			Map entityMap = findEntityMap(rootDir, cx, entityName, dbMap, outDated, dbFound);
			results.put(entityName, entityMap);
		}
		
		int totalNb = listing.size();
		int newNb = totalNb - foundNb;
		
		System.out.println("ENTITY SRC LOADING:");
		System.out.println("- Total nb: "+totalNb);
		System.out.println("- Found nb: "+foundNb);
		System.out.println("- New nb: "+newNb);
		System.out.println("- Out dated nb: "+outDatedNb);
		System.out.println("- Analyzed nb: "+analyzedNb);
		
		return results;
	}
	
	
	
	private Map findEntityMap(File rootDir, Connection cx, String entityName, Map dbMap, boolean outDated, boolean dbFound) throws Exception
	{
		if(!outDated && dbFound) return (Map) dbMap.get(entityName);
		return analyzeEntity(rootDir, cx, entityName, dbFound);
	}
	
	
	
	private Map analyzeEntity(File rootDir, Connection cx, String entityName, boolean dbFound) throws Exception
	{
		Map entityMap = (Map) analyzeEntity.t(new Object[] {entityName, rootDir});
		
		if(dbFound)
		{
			updateEntity.p(new Object[] {cx,entityMap});
			
			deleteServices.p(new Object[] {cx,entityName});
			deleteResources.p(new Object[] {cx,entityName});
			
			insertServices.p(new Object[] {cx,entityMap});
			insertResources.p(new Object[] {cx,entityMap});
		}
		else
		{
			insertEntity.p(new Object[] {cx,entityMap});
			
			insertServices.p(new Object[] {cx,entityMap});
			insertResources.p(new Object[] {cx,entityMap});
		}
		
		return entityMap;
	}
}
