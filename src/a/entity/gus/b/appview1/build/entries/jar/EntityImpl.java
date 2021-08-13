package a.entity.gus.b.appview1.build.entries.jar;

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
	public String creationDate() {return "20210813";}
	
	
	public Object t(Object obj) throws Exception {
		File jarFile = (File) obj;

		List entries = new ArrayList<>();
		JarFile jar = new JarFile(jarFile,true,JarFile.OPEN_READ);
		
		Enumeration en = jar.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry)en.nextElement();
			entries.add(entry.getName());
		}
		Collections.sort(entries);
		jar.close();
		return entries;
	}
}
