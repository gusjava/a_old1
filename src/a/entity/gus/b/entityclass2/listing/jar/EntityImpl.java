package a.entity.gus.b.entityclass2.listing.jar;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210828";}

	public static final String START = "a/entity/";
	public static final String END = "/EntityImpl.class";

	
	
	public Object t(Object obj) throws Exception {
		File jarFile = (File) obj;

		List entityNames = new ArrayList();
		JarFile jar = new JarFile(jarFile,true,JarFile.OPEN_READ);
		
		Enumeration en = jar.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			String name = entry.getName();
			if(name.startsWith(START) && name.endsWith(END))
				entityNames.add(toEntityName(name));
		}
		Collections.sort(entityNames);
		jar.close();
		return entityNames;
	}
	
	
	private String toEntityName(String entryName)
	{return entryName.substring(START.length(), entryName.length() - END.length()).replace("/",".");}
}
