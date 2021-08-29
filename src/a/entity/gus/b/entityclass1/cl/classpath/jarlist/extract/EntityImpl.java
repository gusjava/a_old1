package a.entity.gus.b.entityclass1.cl.classpath.jarlist.extract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210829";}


	private Service getInputStream;
	private Service transfer;

	public EntityImpl() throws Exception {
		getInputStream = Outside.service(this,"gus.b.config1.inputstream.jar");
		transfer = Outside.service(this,"gus.a.io.transfer");
	}
	
	
	public void p(Object obj) throws Exception {
		File jarFile = (File) obj;
		String jarName = jarFile.getName();
		
		InputStream is = (InputStream) getInputStream.t(jarName);
		if(is==null) throw new Exception("Failed to localize jar named: "+jarName);
		
		jarFile.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(jarFile);
		transfer.p(new Object[] {is, fos});
	}
}
