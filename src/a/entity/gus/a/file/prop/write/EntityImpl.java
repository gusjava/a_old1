package a.entity.gus.a.file.prop.write;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210814";}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Properties prop = (Properties) o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file);
		prop.store(fos,"");
		fos.close();
	}
}