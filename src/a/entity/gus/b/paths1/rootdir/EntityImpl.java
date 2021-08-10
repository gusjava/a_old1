package a.entity.gus.b.paths1.rootdir;

import java.io.File;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210809";}
	
	public static final String KEY_ROOT = "root";

	private Service pathToFile;
	private Service defaultRoot;
	private Map params;
	
	private File rootDir;

	public EntityImpl() throws Exception {
		pathToFile = Outside.service(this,"gus.a.file.build.path.os");
		defaultRoot = Outside.service(this,"gus.b.paths1.rootdir.defaultroot");
		params = (Map) Outside.resource(this,"g#m010.g.param");
	}
	
	
	public Object g() throws Exception {
		if(rootDir==null) rootDir = build();
		return rootDir;
	}
	
	private File build() throws Exception {
		if(params.containsKey(KEY_ROOT))
			return (File) pathToFile.t(params.get(KEY_ROOT));
		return (File) defaultRoot.g();
	}
}
