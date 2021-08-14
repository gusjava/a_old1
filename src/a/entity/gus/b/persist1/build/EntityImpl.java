package a.entity.gus.b.persist1.build;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210814";}


	private Service readString;
	private Service writeString;
	
	private Service readProp;
	private Service writeProp;
	
	private Service dirToSet;


	public EntityImpl() throws Exception
	{
		readString = Outside.service(this,"gus.a.file.string.read");
		writeString = Outside.service(this,"gus.a.file.string.write");
		
		readProp = Outside.service(this,"gus.a.file.prop.read");
		writeProp = Outside.service(this,"gus.a.file.prop.write");
		
		dirToSet = Outside.service(this,"gus.b.dirs1.listing0.files.name0");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((File) obj);}
	
	
	
	
	private class Holder implements R, V, F, G
	{
		private File dir;
		
		public Holder(File dir)
		{
			this.dir = dir;
			dir.mkdirs();
		}
		
		
		public Object r(String key) throws Exception
		{
			File f1 = fileProp(key);
			if(f1.isFile()) return readProp.t(f1);
			
			File f2 = fileTxt(key);
			if(f2.isFile()) return readString.t(f2);
			
			return null;
		}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(obj==null) deleteKey(key);
			else if(obj instanceof String)
				persistString(key,""+obj);
			else if(obj instanceof Number)
				persistString(key,""+obj);
			else if(obj instanceof Boolean)
				persistString(key,""+obj);
			else if(obj instanceof Map)
				persistMap(key,(Map) obj);
				
			else throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		
		
		private void deleteKey(String key) throws Exception
		{
			deleteFile(fileProp(key));
			deleteFile(fileTxt(key));
		}
		
		private void persistString(String key, String s) throws Exception
		{
			if(!dir.exists()) dir.mkdirs();
			writeString.p(new Object[]{fileTxt(key),s});
			deleteFile(fileProp(key));
		}
		
		private void persistMap(String key, Map m) throws Exception
		{
			if(!dir.exists()) dir.mkdirs();
			writeProp.p(new Object[]{fileProp(key),m});
			deleteFile(fileTxt(key));
		}
		
		
		
		public boolean f(Object obj) throws Exception
		{
			if(fileProp((String) obj).isFile()) return true;
			if(fileTxt((String) obj).isFile()) return true;
			
			return false;
		}
		
		public Object g() throws Exception
		{return dirToSet.t(dir);}
		
		private File fileTxt(String key)
		{return new File(dir, key+".txt");}
	
		private File fileProp(String key)
		{return new File(dir, key+".properties");}
	}
	
	
	private void deleteFile(File f) throws Exception
	{
		if(!f.exists()) return;
		boolean r = f.delete();
		if(!r) throw new Exception("Failed to delete file: "+f);
	}
}