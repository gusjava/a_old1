package a.entity.gus.a.jre.prop.javahome.as.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210929";}

	private File file;
	
	public Object g() throws Exception
	{
		if(file==null) init();
		return file;
	}
	
	private void init() throws Exception
	{file = new File(System.getProperty("java.home"));}
}
