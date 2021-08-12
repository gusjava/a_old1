package a.entity.gus.a.file.isfile.casesensitive;

import java.io.File;

import a.framework.Entity;
import a.framework.F;

public class EntityImpl implements Entity, F {
	public String creationDate() {return "20210812";}


	public boolean f(Object obj) throws Exception
	{
		return isRealFile((File) obj);
	}
	
	private boolean isRealFile(File file)
	{
		if(!file.isFile()) return false;
		
		String name = file.getName();
		String[] nn = file.getParentFile().list();
		for(String n : nn) if(n.equals(name)) return true;
		return false;
	}
}