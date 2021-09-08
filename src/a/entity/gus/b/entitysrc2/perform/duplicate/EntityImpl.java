package a.entity.gus.b.entitysrc2.perform.duplicate;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210908";}


	public EntityImpl() throws Exception
	{
		
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		String entityName1 = (String) o[2];
	}
}
