package a.entity.gus.b.icons1.loader.outside.dir;

import java.io.File;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T, F {
	public String creationDate() {return "20210812";}


	private Service readIcon;
	private Service isFile;
	
	public EntityImpl() throws Exception
	{
		readIcon = Outside.service(this,"gus.a.file.icon.read.imageio");
		isFile = Outside.service(this,"gus.a.file.isfile.casesensitive");
	}
		
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return load((File) o[0], (String) o[1]);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return hasKey((File) o[0], (String) o[1]);
	}
	
	
	private Icon load(File dir, String key) throws Exception
	{
		File gifFile = new File(dir,key+".gif");
		if(isFile.f(gifFile)) return readIcon(gifFile);
		
		File pngFile = new File(dir,key+".png");
		if(isFile.f(pngFile)) return readIcon(pngFile);
		
		return null;
	}
	
	private Icon readIcon(File f) throws Exception
	{return (Icon) readIcon.t(f);}
	
	
	
	private boolean hasKey(File dir, String key) throws Exception
	{
		File gifFile = new File(dir,key+".gif");
		if(isFile.f(gifFile)) return true;
		
		File pngFile = new File(dir,key+".png");
		if(isFile.f(pngFile)) return true;
		
		return false;
	}
}