package a.entity.gus.b.paths1.guslink;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210810";}

	public static final String GUSLINK_FILENAME = "guslink.txt";
	
	
	private Service readFile;
	
	public EntityImpl() throws Exception {
		readFile = Outside.service(this,"gus.a.file.string.read");
	}
	
	
	public Object t(Object obj) throws Exception {
		File f = (File) obj;
		if(f==null) return null;

		List parents = buildParents(f);
		for(int i=0;i<parents.size();i++) {
			File f1 = (File) parents.get(i);
			File f2 = findLink(f1);
			if(f2!=null) return redirect(f,f1,f2);
		}
		if(f.isDirectory()) {
			File f2 = findLink(f);
			if(f2!=null) return f2;
		}
		return f;
	}
	
	
	
	private File findLink(File dir) throws Exception {
		File linkFile = new File(dir,GUSLINK_FILENAME);
		if(!linkFile.isFile()) return null;
		
		String link = (String) readFile.t(linkFile);
		if(link==null || link.trim().equals("")) return null;
		
		return new File(link);
	}
	
	
	private File redirect(File f, File f1, File f2) throws Exception {
		String p = f.getAbsolutePath();
		String p1 = f1.getAbsolutePath();
		String r = p.substring(p1.length());
		
		return new File(f2,r);
	}
	
	
	
	private List buildParents(File f) {
		List list = new ArrayList();
		while(f.getParentFile()!=null)
		{
			f = f.getParentFile();
			list.add(f);
		}
		Collections.reverse(list);
		return list;
	}
}
