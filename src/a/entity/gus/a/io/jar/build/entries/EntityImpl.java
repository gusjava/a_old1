package a.entity.gus.a.io.jar.build.entries;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210822";}

	
	
	public Object t(Object obj) throws Exception {
		InputStream is = (InputStream) obj;
		JarInputStream jis = new JarInputStream(is);
		
		List list = new ArrayList();
		JarEntry je;
        while((je=jis.getNextJarEntry())!=null){
        	list.add(je.getName());
        }
        jis.close();
		return list;
	}
}
