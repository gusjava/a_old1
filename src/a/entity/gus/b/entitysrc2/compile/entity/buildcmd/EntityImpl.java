package a.entity.gus.b.entitysrc2.compile.entity.buildcmd;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20211003";}
	
	
	public Object t(Object obj) throws Exception
	{
		File[] f = (File[]) obj;
		if(f.length!=5) throw new Exception("Wrong data number: "+f.length);
		
		File javacFile = f[0];
		File libDir = f[1];
		File srcDir = f[2];
		File binDir = f[3];
		File listingFile = f[4];
		
		if(libDir!=null && libDir.isDirectory())
		{
			return new String[]{
				p(javacFile),
				"-classpath",	p(libDir)+"/*",
				"-sourcepath",	p(srcDir),
				"-d",		p(binDir),
				"@"+		p(listingFile)
			};
		}
		return new String[]{
			p(javacFile),
			"-sourcepath",	p(srcDir),
			"-d",		p(binDir),
			"@"+		p(listingFile)
		};
	}
	
	
	private String p(File f)
	{return f.getAbsolutePath();}
}
