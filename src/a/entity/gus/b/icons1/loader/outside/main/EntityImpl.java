package a.entity.gus.b.icons1.loader.outside.main;

import java.io.File;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T, F, R {
	public String creationDate() {return "20210812";}

	private Service load;
	
	private File storeDir;
	
	public EntityImpl() throws Exception
	{
		load = Outside.service(this,"gus.b.icons1.loader.outside.dir");
		
		storeDir = (File) Outside.service(this,"gus.b.paths1.dir.icondir").g();
		if(storeDir==null) throw new Exception("Icon dir could not be found");
		storeDir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{return storeDir;}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return load.f(new Object[]{storeDir,(String) obj});}
	
	
	public Object r(String key) throws Exception
	{return load.t(new Object[]{storeDir,key});}
}