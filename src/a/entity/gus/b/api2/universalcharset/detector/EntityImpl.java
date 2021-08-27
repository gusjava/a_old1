package a.entity.gus.b.api2.universalcharset.detector;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.mozilla.universalchardet.UniversalDetector;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.T;

public class EntityImpl implements Entity, T, G {
	public String creationDate() {return "20210820";}


	public EntityImpl() throws Exception {
		
	}
	
	
	public Object t(Object obj) throws Exception {
		File file = (File) obj;
		if(file==null || !file.isFile()) return null;

		UniversalDetector detector = new UniversalDetector(null);
		
		try(FileInputStream fis = new FileInputStream(file)) {
			byte[] buf = new byte[4096];
			int nread;
	
			while((nread = fis.read(buf)) > 0 && !detector.isDone())
			detector.handleData(buf, 0, nread);
			detector.dataEnd();
		}

		String name = detector.getDetectedCharset();
		detector.reset();
		
		if(name==null) return Charset.defaultCharset();
		
		// BUG de détection quand on a un fichier texte windows-1252 avec des éé
		if(name.equals("WINDOWS-1255")) name = "WINDOWS-1252";
		
		try{return Charset.forName(name);}
		catch(Exception e)
		{
    			String message = "Charset not found for name: "+name;
    			Outside.err(this,"t(Object)",new Exception(message,e));
    			return Charset.defaultCharset();
		}
	}
	
	
	public Object g() throws Exception {
		return new UniversalDetector(null);
	}
}
