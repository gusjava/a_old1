package a.entity.gus.b.entitysrc2.database.entity.findall.asmap;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}


	public EntityImpl() throws Exception {
		
	}
	
	
	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		Map data = new HashMap();
		
		return data;
	}
}
