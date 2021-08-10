package a.entity.gus.a.file.build.path.os;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210809";}
	
	
	public Object t(Object obj) throws Exception {
		if(obj instanceof String) {
			return new File(pathOS((String) obj)).getAbsoluteFile();
		}
		if(obj instanceof Object[]) {
			Object[] data = (Object[]) obj;
			return new File((File) data[0], pathOS((String) data[1])).getAbsoluteFile();
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String pathOS(String path) {
		if(path.startsWith("<user.home>"))
		path = path.replace("<user.home>",System.getProperty("user.home"));
		
		if(path.startsWith("<java.home>"))
		path = path.replace("<java.home>",System.getProperty("java.home"));
		
		String s = File.separator;
		path = path.replace("\\",s).replace("/",s).replace(s+s,s);
		if(path.startsWith(s)) path = path.substring(1);
		if(path.endsWith(s)) path = path.substring(0,path.length()-1);

		return path;
	}
}
