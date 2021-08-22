package a.entity.gus.b.files1.icon.ext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T, R, G {
	public String creationDate() {return "20210812";}

	public static final String DIR = "#";

	
	private Service iconOs;
	private Service fileSample;
	private Service loadIcon;
	
	private Map cache;
	
	
	public EntityImpl() throws Exception
	{
		iconOs = Outside.service(this,"gus.a.file.icon.os");
		fileSample = Outside.service(this,"gus.b.files1.ext.sample");
		loadIcon = Outside.service(this,"gus.b.icons1.loader");
		
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
			cache.put(ext, build(ext));
		return cache.get(ext);
	}
	
	
	private Icon build(String ext) throws Exception
	{
		String iconId = iconId(ext);
		Icon icon = (Icon) loadIcon.t(iconId);
		if(icon!=null) return icon;
		
		File file = sampleFile(ext);
		return (Icon) iconOs.t(file);
	}
	
	
	private String iconId(String ext) throws Exception {
		if(ext.equals(DIR)) return "dir";
		return "FILE_" + ext;
	}
	
	private File sampleFile(String ext) throws Exception {
		if(ext.equals(DIR)) return (File) fileSample.g();
		return (File) fileSample.t(ext);
	}
}