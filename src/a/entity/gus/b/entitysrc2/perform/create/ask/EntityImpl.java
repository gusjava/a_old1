package a.entity.gus.b.entitysrc2.perform.create.ask;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210908";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "pending");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Object anchor = o[1];
		
		perform.p("create new entity");
	}
}
