package a.entity.gus.b.appview1.handle.entryinputstream.dir;

import java.io.File;
import java.io.FileInputStream;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210813";}
	
	
	public void p(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		
		File location = (File) data[0];
		String entry = (String) data[1];
		P handler = (P) data[2];
		
		File entryFile = new File(location, entry);
		if(!entryFile.isFile()) return;
		
		try(FileInputStream fis = new FileInputStream(entryFile)) {
			handler.p(fis);
		}
	}
}
