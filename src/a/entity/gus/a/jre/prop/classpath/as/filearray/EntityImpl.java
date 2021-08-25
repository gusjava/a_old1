package a.entity.gus.a.jre.prop.classpath.as.filearray;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210825";}

	private File[] files;
	
	
	public Object g() throws Exception
	{
		if(files==null) init();
		return files;
	}
	
	
	private void init() throws Exception
	{
		String ext = System.getProperty("java.class.path");
		String[] n = ext.split(File.pathSeparator);
		
		files = new File[n.length];
		for(int i=0;i<n.length;i++) files[i] = new File(n[i]); 
	}
}
