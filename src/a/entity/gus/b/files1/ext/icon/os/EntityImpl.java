package a.entity.gus.b.files1.ext.icon.os;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


public static final String DIR = "#";

	
	private Service fileToIcon;
	private Service fileSample;
	
	private Map cache;
	
	
	public EntityImpl() throws Exception
	{
		fileToIcon = Outside.service(this,"gus.a.file.icon.os");
		fileSample = Outside.service(this,"gus.b.files1.ext.sample");
		
		cache = new HashMap();
	}
	
	
	public Object g() throws Exception
	{return r(DIR);}
	
	
	public Object r(String key) throws Exception
	{return t(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String ext = (String) obj;
		if(!cache.containsKey(ext))
			cache.put(ext,build(ext));
		return cache.get(ext);
	}
	
	
	private Icon build(String ext) throws Exception
	{
		if(ext.equals(DIR)) return (Icon) fileToIcon.t(fileSample.g());
		
		File file = (File) fileSample.t(ext);
		return (Icon) fileToIcon.t(file);
	}
}