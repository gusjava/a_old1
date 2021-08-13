package a.entity.gus.a.app.location;

import java.io.File;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210813";}


	private File location;
	
	public Object g() throws Exception
	{
		if(location==null)
    		location = find();
		return location;
	}
	
	private File find() throws Exception
	{return new File(Outside.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());}
}