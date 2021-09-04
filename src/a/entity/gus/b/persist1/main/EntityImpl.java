package a.entity.gus.b.persist1.main;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G, F, R, V {
	public String creationDate() {return "20210814";}


	private Service changeDetector;
	private Service builder;
	private Object holder;
	private File dir;
	


	public EntityImpl() throws Exception
	{
		changeDetector = Outside.service(this,"*gus.b.changedetect1.check");
		builder = Outside.service(this,"gus.b.persist1.build");
		dir = (File) Outside.resource(this,"path#path.persistencedir");
		
		if(dir==null) throw new Exception("Persistence dir not found");
		holder = builder.t(dir);
	}
	
	public Object r(String key) throws Exception
	{
		Object value = ((R) holder).r(key);
		if(value instanceof String) changeDetector.p(new String[] {key, (String)value});
		return value;
	}
	
	public void v(String key, Object obj) throws Exception
	{
		boolean changed = obj instanceof String ? changeDetector.f(new String[] {key, (String) obj}) : true;
		if(changed) ((V) holder).v(key,obj);
	}
	
	public Object g() throws Exception
	{return ((G) holder).g();}
	
	public boolean f(Object obj) throws Exception
	{return ((F) holder).f(obj);}
}