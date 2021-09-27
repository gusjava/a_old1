package a.entity.gus.b.entitysrc2.database.entity_link.find2.sorted;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210927";}
	
	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus.b.entitysrc2.database.entity_link.find2");
	}
	
	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList((Set) find.t(obj));
		Collections.sort(list);
		return list;
	}
}
