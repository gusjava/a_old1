package a.entity.gus.b.entitysrc2.database.entity.remover;

import java.sql.Connection;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210914";}


	private Service deleteEntity;
	private Service deleteServices;
	private Service deleteResources;
	private Service deleteLinks1;
	private Service deleteLinks2;
	
	private Service deleteEntityIn;
	private Service deleteServicesIn;
	private Service deleteResourcesIn;
	private Service deleteLinks1In;
	private Service deleteLinks2In;
	

	public EntityImpl() throws Exception
	{
		deleteEntity = Outside.service(this,"gus.b.entitysrc2.database.entity.delete");
		deleteServices = Outside.service(this,"gus.b.entitysrc2.database.entity_service.delete");
		deleteResources = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.delete");
		deleteLinks1 = Outside.service(this,"gus.b.entitysrc2.database.entity_link.delete1");
		deleteLinks2 = Outside.service(this,"gus.b.entitysrc2.database.entity_link.delete2");
		
		deleteEntityIn = Outside.service(this,"gus.b.entitysrc2.database.entity.delete.in");
		deleteServicesIn = Outside.service(this,"gus.b.entitysrc2.database.entity_service.delete.in");
		deleteResourcesIn = Outside.service(this,"gus.b.entitysrc2.database.entity_resource.delete.in");
		deleteLinks1In = Outside.service(this,"gus.b.entitysrc2.database.entity_link.delete1.in");
		deleteLinks2In = Outside.service(this,"gus.b.entitysrc2.database.entity_link.delete2.in");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Object data = o[1];
		
		if(data instanceof String) remove(cx, (String) data);
		else if(data instanceof Set) remove(cx, (Set) data);
		else throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private void remove(Connection cx, String name) throws Exception {
		deleteLinks1.p(new Object[] {cx, name});
		deleteLinks2.p(new Object[] {cx, name});
		deleteServices.p(new Object[] {cx, name});
		deleteResources.p(new Object[] {cx, name});
		deleteEntity.p(new Object[] {cx, name});
	}
	
	private void remove(Connection cx, Set names) throws Exception {
		deleteLinks1In.p(new Object[] {cx, names});
		deleteLinks2In.p(new Object[] {cx, names});
		deleteServicesIn.p(new Object[] {cx, names});
		deleteResourcesIn.p(new Object[] {cx, names});
		deleteEntityIn.p(new Object[] {cx, names});
	}
}
