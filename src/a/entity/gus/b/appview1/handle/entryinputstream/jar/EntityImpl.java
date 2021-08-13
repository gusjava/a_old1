package a.entity.gus.b.appview1.handle.entryinputstream.jar;

import java.io.File;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210813";}

	
	public void p(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		
		File location = (File) data[0];
		String entry = (String) data[1];
		P handler = (P) data[2];
		
		JarFile jarFile = new JarFile(location, true, JarFile.OPEN_READ);
		JarEntry jarEntry = jarFile.getJarEntry(entry);
		if(jarEntry==null) {
			jarFile.close();
			throw new Exception("JarEntry not found: "+entry+" for file "+location);
		}
		
		try(InputStream is = jarFile.getInputStream(jarEntry)) {
			handler.p(is);
		}
		jarFile.close();
	}
}
