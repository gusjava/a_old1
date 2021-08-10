package a.entity.gus.b.paths1.rootdir.defaultroot;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210809";}

	private Service getUserHome;
	private String appName;
	
	private File dir;

	public EntityImpl() throws Exception {
		getUserHome = Outside.service(this,"gus.a.file.get.os.userhome");
		appName = (String) Outside.service(this,"m009.g.config.id").g();
	}
	
	
	public Object g() throws Exception {
		if(dir==null) init();
		return dir;
	}
	
	
	private void init() throws Exception {
		File d1 = (File) getUserHome.g();
		File d2 = new File(d1,".framework_a");
		File d3 = new File(d2,"applis");
		
		dir = new File(d3,appName);
		dir.mkdirs();
	}
}
