package a.entity.gus.b.dataview1.object.builder;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210811";}

	
	private Service findNames;
	private Service newEntity;

	public EntityImpl() throws Exception {
		findNames = Outside.service(this,"gus.b.dataview1.object.builder.name");
		newEntity = Outside.service(this,"newentity");
	}
	
	
	public Object t(Object obj) throws Exception {
		Map names = (Map) findNames.t(obj);
		
		Map map = new LinkedHashMap();
		
		Iterator it = names.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			String entityName = (String) names.get(key);
			Object viewer = newEntity.t(entityName);
			
			map.put(key,viewer);
			((P) viewer).p(obj);
		}
		return map;
	}
}
