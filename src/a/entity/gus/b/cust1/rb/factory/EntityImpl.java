package a.entity.gus.b.cust1.rb.factory;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210811";}

	
	private Service newEntity;

	public EntityImpl() throws Exception {
		newEntity = Outside.service(this,"newentity");
	}
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		String entityName = (String) data[1];
		return new Factory(entityName);
	}
	
	private class Factory implements G {
		private String entityName;
		
		public Factory(String entityName) {
			this.entityName = entityName;
		}
		
		public Object g() throws Exception {
			return newEntity.t(entityName);
		}
	}
}
