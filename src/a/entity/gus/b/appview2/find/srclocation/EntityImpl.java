package a.entity.gus.b.appview2.find.srclocation;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210823";}
	
	
	public Object t(Object obj) throws Exception {
		File appLocation = (File) obj;
		if(appLocation==null || !appLocation.exists()) return null;
		
		if(appLocation.isDirectory()) {
			File root = appLocation.getParentFile();
			File srcDir = new File(root,"src");
			if (srcDir.isDirectory()) return srcDir;
		}
		return appLocation;
	}
}
