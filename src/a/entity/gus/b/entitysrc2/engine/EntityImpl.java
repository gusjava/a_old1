package a.entity.gus.b.entitysrc2.engine;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210830";}


	private Service analyzeEntity;
	private Service getListing;
	private Service findAll;
	private Service remover;
	
	private Service insertEntity;
	private Service updateEntity;
	
	private Service insertServices;
	private Service insertResources;
	private Service insertLinks;
	
	private Service deleteServices;
	private Service deleteResources;
	private Service deleteLinks;
	

	
	public EntityImpl() throws Exception {
		analyzeEntity = Outside.service(this,"gus.b.entitysrc2.analyze.entity");
		getListing = Outside.service(this,"gus.b.entitysrc2.listing.lastmodified");
		findAll = Outside.service(this,"gus.b.entitysrc2.database.entity.findall.asmap");
		remover = Outside.service(this,"gus.b.entitysrc2.database.entity.remover");
		
		insertEntity = Outside.service(this,"gus.b.entitysrc2.database.entity.insert");
		updateEntity = Outside.service(this,"gus.b.entitysrc2.database.entity.update");
		
		insertServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.insert");
		deleteServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.delete");
		
		insertResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.insert");
		deleteResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.delete");
		
		insertLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.insert");
		deleteLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.delete1");
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = (File) o[0];
		Connection cx = (Connection) o[1];
		Long lastTime = (Long) o[2];
		
		long t1 = System.currentTimeMillis();
		
		Map mapRoot = (Map) getListing.t(rootDir);
		Set setRoot = mapRoot.keySet();
		
		Map mapDb = (Map) findAll.t(cx);
		if(mapDb==null) throw new Exception("null data retrieved from cx");
		
		Set over = new HashSet(mapDb.keySet());
		over.removeAll(setRoot);
		if(!over.isEmpty()) remover.p(new Object[] {cx, over});
		
		Map results = new HashMap();
		Set analyzed = new HashSet();
		
		int dirExistingNb = 0;
		int dirOutDatedNb = 0;
		int dirAnalyzedNb = 0;
		
		Iterator it = setRoot.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			long lastModified = (long) mapRoot.get(entityName);
			
			boolean outDated = lastModified > lastTime;
			boolean dbFound = mapDb.containsKey(entityName);
			boolean shouldAnalyze = outDated || !dbFound;
			
			if(dbFound) dirExistingNb++;
			if(outDated) dirOutDatedNb++;
			if(shouldAnalyze) dirAnalyzedNb++;
			
			Map entityMap = null;
			
			if(shouldAnalyze) {
				entityMap = (Map) analyzeEntity.t(new Object[] {entityName, rootDir, setRoot});
				analyzed.add(entityName);
				
				if(dbFound) updateEntity.p(new Object[] {cx,entityMap});
				else insertEntity.p(new Object[] {cx,entityMap});
			}
			else entityMap = (Map) mapDb.get(entityName);
			
			results.put(entityName, entityMap);
		}
		
		it = analyzed.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			Map entityMap = (Map) results.get(entityName);
			
			if(mapDb.containsKey(entityName)) {
				deleteServices.p(new Object[] {cx, entityName});
				deleteResources.p(new Object[] {cx, entityName});
				deleteLinks.p(new Object[] {cx, entityName});
			}
			insertServices.p(new Object[] {cx, entityMap});
			insertResources.p(new Object[] {cx, entityMap});
			insertLinks.p(new Object[] {cx, entityMap});
		}
		
		long duration = System.currentTimeMillis()-t1;
		
//		int dbTotalNb = mapDb.size();
//		int dbRemovedNb = over.size();
//		int dirTotalNb = mapRoot.size();
//		int dirNewNb = dirTotalNb - dirExistingNb;
//		
//		System.out.println("ENTITY SRC LOADING:");
//		System.out.println("- DB total: "+dbTotalNb);
//		System.out.println("- DB removed: "+dbRemovedNb);
//		System.out.println("- DIR total: "+dirTotalNb);
//		System.out.println("- DIR existing: "+dirExistingNb);
//		System.out.println("- DIR new: "+dirNewNb);
//		System.out.println("- DIR outdated: "+dirOutDatedNb);
//		System.out.println("- DIR analyzed: "+dirAnalyzedNb);
//		System.out.println("- duration: "+duration);
		
		System.out.println("Analyzed entity nb: "+dirAnalyzedNb+" (duration="+duration+")");
		
		return results;
	}
}
