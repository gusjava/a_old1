package a.entity.gus.b.entitysrc2.engine.dataloader;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210830";}


	private Service analyzeEntity;
	private Service compileEntity;
	private Service getListing;
	private Service findAll;
	private Service remover;
	
	private Service insertEntity;
	private Service updateEntity;
	
	private Service insertServices;
	private Service deleteServices;
	
	private Service insertResources;
	private Service deleteResources;
	
	private Service insertLinks;
	private Service deleteLinks;
	

	
	public EntityImpl() throws Exception
	{
		analyzeEntity = Outside.service(this,"gus.b.entitysrc2.analyze.entity");
		compileEntity = Outside.service(this,"gus.b.entitysrc2.compile.entity");
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
		Object engine = obj;
		
		Long lastTime = (Long) ((R) engine).r("lastTime");
		File rootDir = (File) ((R) engine).r("rootDir");
		Connection cx = (Connection) ((R) engine).r("cx");
		
		
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
		
		int dirAnalyzedNb = 0;
		
		Iterator it = setRoot.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			long lastModified = (long) mapRoot.get(entityName);
			
			boolean outDated = lastModified > lastTime;
			boolean dbFound = mapDb.containsKey(entityName);
			boolean shouldAnalyze = outDated || !dbFound;
			
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
			
			compileEntity.p(new Object[] {engine, entityName});
		}
		
		long duration = System.currentTimeMillis()-t1;
		System.out.println("Analyzed entity nb: "+dirAnalyzedNb+" (duration="+duration+")");
		
		return results;
	}
}
