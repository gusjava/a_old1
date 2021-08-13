package a.entity.gus.b.appview1.handle.entryinputstream;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210813";}


	private Service handleJar;
	private Service handleDir;

	public EntityImpl() throws Exception {
		handleJar = Outside.service(this,"gus.b.appview1.handle.entryinputstream.jar");
		handleDir = Outside.service(this,"gus.b.appview1.handle.entryinputstream.dir");
	}
	
	
	public void p(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		File location = (File) data[0];
		
		if(location==null) return;
		if(location.isFile()) {
			handleJar.p(data);
			return;
		}
		if(location.isDirectory()) {
			handleDir.p(data);
			return;
		}
	}
}
