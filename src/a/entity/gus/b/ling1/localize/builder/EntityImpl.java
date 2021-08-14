package a.entity.gus.b.ling1.localize.builder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210814";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus.b.ling1.localize.perform");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		return new Holder((String) o[0], o[1]);
	}
	
	
	
	private class Holder implements E, R
	{
		private Object obj;
		private String lingKey;
	
		public Holder(String lingKey, Object obj)
		{
			this.lingKey = lingKey;
			this.obj = obj;
		}
	
		public void e() throws Exception
		{
			perform.v(lingKey, obj);
		}
	
		public Object r(String key) throws Exception
		{
			if(key.equals("lingKey")) return lingKey;
			if(key.equals("obj")) return obj;
			if(key.equals("keys")) return new String[]{"lingKey","obj"};
			
			throw new Exception("Unknown key: "+key);
		}
	}
}
