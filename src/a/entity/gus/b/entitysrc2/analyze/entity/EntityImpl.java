package a.entity.gus.b.entitysrc2.analyze.entity;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}
	
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_FEATURES = "features";
	public static final String KEY_CREATIONDATE = "creationdate";
	
	public static final String KEY_ENTITYNAME = "entityname";
	public static final String KEY_LENGTH = "length";

	
	private Service nameToFile;
	private Service readFile;
	private Service extract;

	public EntityImpl() throws Exception {
		nameToFile = Outside.service(this,"gus.b.entitysrc2.nametofile");
		readFile = Outside.service(this,"gus.a.file.string.read");
		extract = Outside.service(this,"gus.b.entitysrc2.analyze.entity.extract");
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String entityName = (String) o[0];
		File rootDir = (File) o[1];
		Connection cx = (Connection) o[2];
		
		File javaFile = (File) nameToFile.t(new Object[] {rootDir, entityName});
		Map data = extractDataFrom(javaFile, entityName);
		//record data inside BDD
		return data;
	}
	
	
	private Map extractDataFrom(File javaFile, String entityName) throws Exception {
		try {
			String javaSrc = (String) readFile.t(javaFile);
			int length = javaSrc.length();
			
			Map data = (Map) extract.t(javaSrc);
			
			if(!data.containsKey(KEY_PACKAGE)) throw new Exception("Package not found");
			if(!data.containsKey(KEY_FEATURES)) throw new Exception("Features not found");
			if(!data.containsKey(KEY_CREATIONDATE)) throw new Exception("Creation date not found");
			
			String package1 = (String) data.get(KEY_PACKAGE);
			if(!package1.equals("a.entity."+entityName)) throw new Exception("Invalid package value: "+package1);
			
			data.put(KEY_ENTITYNAME, entityName);
			data.put(KEY_LENGTH, length);
			return data;
		}
		catch(Exception e) {
			String message = "Data extraction failed for file: "+javaFile;
			throw new Exception(message, e);
		}
	}
}
