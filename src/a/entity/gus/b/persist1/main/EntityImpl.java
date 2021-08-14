package a.entity.gus.b.persist1.main;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G, F, R, V {
	public String creationDate() {return "20210814";}


	private Service builder;
	private Object holder;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus.b.persist1.build");
		File dir = (File) Outside.resource(this,"path#path.persistencedir");
		holder = builder.t(dir);
	}
	
	public Object r(String key) throws Exception
	{return ((R) holder).r(key);}
	
	public void v(String key, Object obj) throws Exception
	{((V) holder).v(key,obj);}
	
	public Object g() throws Exception
	{return ((G) holder).g();}
	
	public boolean f(Object obj) throws Exception
	{return ((F) holder).f(obj);}
}