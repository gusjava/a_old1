package a.entity.gus.b.entitysrc2.engine;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	private Service deleteEntityIn;
	private Service insertEntity;
	private Service updateEntity;
	private Service insertServices;
	private Service deleteServices;
	private Service deleteServicesIn;
	private Service insertResources;
	private Service deleteResources;
	private Service deleteResourcesIn;

	
	public EntityImpl() throws Exception {
		analyzeEntity = Outside.service(this,"gus.b.entitysrc2.analyze.entity");
		getListing = Outside.service(this,"gus.b.entitysrc2.listing.lastmodified");
		findAll = Outside.service(this,"gus.b.entitysrc2.database.entity.findall.asmap");
		deleteEntityIn = Outside.service(this,"gus.b.entitysrc2.database.entity.delete.in");
		insertEntity = Outside.service(this,"gus.b.entitysrc2.database.entity.insert");
		updateEntity = Outside.service(this,"gus.b.entitysrc2.database.entity.update");
		insertServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.insert");
		deleteServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.delete");
		deleteServicesIn = Outside.service(this,"gus.b.entitysrc2.database.entity_service.delete.in");
		insertResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.insert");
		deleteResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.delete");
		deleteResourcesIn = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.delete.in");
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = (File) o[0];
		Connection cx = (Connection) o[1];
		Long lastTime = (Long) o[2];
		
		long t1 = System.currentTimeMillis();
		
		Map listing = (Map) getListing.t(rootDir);
		Map dbMap = (Map) findAll.t(cx);
		if(dbMap==null) throw new Exception("null data retrieved from cx");
		
		List over = new ArrayList(dbMap.keySet());
		over.removeAll(listing.keySet());
		if(!over.isEmpty()) {
			deleteServicesIn.p(new Object[] {cx, over});
			deleteResourcesIn.p(new Object[] {cx, over});
			deleteEntityIn.p(new Object[] {cx, over});
		}
		
		Map results = new HashMap();
		
		int dirExistingNb = 0;
		int dirOutDatedNb = 0;
		int dirAnalyzedNb = 0;
		
		Iterator it = listing.keySet().iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			long lastModified = (long) listing.get(entityName);
			
			boolean outDated = lastModified > lastTime;
			boolean dbFound = dbMap.containsKey(entityName);
			
			if(dbFound) dirExistingNb++;
			if(outDated) dirOutDatedNb++;
			if(outDated || !dbFound) dirAnalyzedNb++;
			
			Map entityMap = findEntityMap(rootDir, cx, entityName, dbMap, outDated, dbFound);
			results.put(entityName, entityMap);
		}
		
		int dbTotalNb = dbMap.size();
		int dbRemovedNb = over.size();
		int dirTotalNb = listing.size();
		int dirNewNb = dirTotalNb - dirExistingNb;
		
		System.out.println("ENTITY SRC LOADING:");
		System.out.println("- DB total: "+dbTotalNb);
		System.out.println("- DB removed: "+dbRemovedNb);
		System.out.println("- DIR total: "+dirTotalNb);
		System.out.println("- DIR existing: "+dirExistingNb);
		System.out.println("- DIR new: "+dirNewNb);
		System.out.println("- DIR outdated: "+dirOutDatedNb);
		System.out.println("- DIR analyzed: "+dirAnalyzedNb);

		long t2 = System.currentTimeMillis();
		System.out.println("- duration: "+(t2-t1));
		
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
