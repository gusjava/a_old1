package a.entity.gus.b.icons1.fileext.icon.os;

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

	
	private Service iconOs;
	private Service fileSample;
	
	private Map cache;
	private Icon dirIcon;
	
	
	public EntityImpl() throws Exception
	{
		iconOs = Outside.service(this,"gus.a.file.icon.os");
		fileSample = Outside.service(this,"gus.b.files1.ext.sample");
		
		cache = new HashMap();
		dirIcon = build(null);
	}
	
	
	public Object r(String key) throws Exception
	{return t(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return dirIcon;
		return iconForExt((String) obj);
	}
	
	
	private Icon iconForExt(String ext) throws Exception
	{
		if(!cache.containsKey(ext))
			cache.put(ext, build(ext));
		return (Icon) cache.get(ext);
	}
	
	private Icon build(String ext) throws Exception
	{
		File file = (File) fileSample.t(ext);
		return (Icon) iconOs.t(file);
	}
}