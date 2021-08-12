package a.entity.gus.b.paths1.provider.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, G, R {
	public String creationDate() {return "20210811";}

	public static final String PATH_ROOTDIR = "path.rootdir";
	
	private Service engine;
	private Map prop;
	private File rootDir;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "*gus.b.paths1.provider.engine");
		prop = (Map) Outside.service(this,"m003.g.prop").g();
		rootDir = (File) Outside.service(this,"gus.b.paths1.rootdir").g();
		
		Map paths = new HashMap();
		paths.put(PATH_ROOTDIR, rootDir);
		
		engine.v("prop", prop);
		engine.v("paths", paths);
		engine.e();
	}
	
	
	public Object g() throws Exception {
		return engine.g();
	}
	
	
	public Object r(String key) throws Exception {
		return engine.r(key);
	}
}
