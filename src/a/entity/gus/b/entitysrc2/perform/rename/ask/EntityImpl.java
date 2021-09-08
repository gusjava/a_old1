package a.entity.gus.b.entitysrc2.perform.rename.ask;

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
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		Object anchor = o[2];
		
		perform.p("rename "+entityName);
	}
}
