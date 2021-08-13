package a.entity.gus.b.api1.jdepend.analyze.data;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}

	private ClassFileParser parser;

	public EntityImpl() throws Exception
	{
		parser = new ClassFileParser();
	}


	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		JavaClass jClass = parser.parse(is);
		
		Map data = new HashMap();
		
		data.put("imports",jClass.getImportedPackageNames());
		data.put("majorversion", Integer.valueOf(jClass.majorVersion));
		data.put("minorversion", Integer.valueOf(jClass.minorVersion));
		
		return data;
	}
}