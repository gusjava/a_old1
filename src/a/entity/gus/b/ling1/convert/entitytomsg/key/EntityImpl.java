package a.entity.gus.b.ling1.convert.entitytomsg.key;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210816";}

	private Service findName;
	private Service findMsg;

	public EntityImpl() throws Exception
	{
		findName = Outside.service(this,"entityname");
		findMsg = Outside.service(this,"gus.b.ling1.msg.find");
	}
	
	public Object t(Object obj) throws Exception
	{
		String entityName = (String) findName.t(obj);
		String lingKey = "m_entity_"+entityName;
		boolean found = findMsg.f(lingKey);
		return found ? lingKey : null;
	}
}
