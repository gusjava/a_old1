package a.entity.gus.b.paths1.provider.engine;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, G, R, V, E {
	public String creationDate() {return "20210810";}
	
	
	private static final Pattern PATTERN_TAG = Pattern.compile("<([^>]+)>");
	private static final Pattern PATTERN_MOVE = Pattern.compile("\\[(-?[0-9]+)\\]");
	private static final int LOOKUP_LIMIT = 200;

	
	private Service pathToFile;
	private Service handleGusLink;
	
	private Map paths;
	private Map prop;

	public EntityImpl() throws Exception {
		pathToFile = Outside.service(this,"gus.a.file.build.path.os");
		handleGusLink = Outside.service(this,"gus.b.paths1.guslink");
		paths = new HashMap();
	}
	
	public Object g() throws Exception {
		return paths;
	}
	
	
	public void v(String key, Object obj) throws Exception {
		if(key.equals("prop")) {prop = (Map) obj; return;}
		if(key.equals("paths")) {paths = (Map) obj; return;}
	}
	
	
	public void e() throws Exception {
		Iterator it = prop.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			if(key.startsWith("path.")) r(key);
		}
	}
	
	
	public Object r(String key) throws Exception {
		return searchFile(key, 0);
	}
	
	
	
	private File searchFile(String info, int c) throws Exception {
		if(c>LOOKUP_LIMIT)
			throw new Exception("Infinite loop detected: stopping at value "+info);
		
		if(paths.containsKey(info))
			return (File) paths.get(info);
		
		if(info.startsWith("path.")) {
			if(!prop.containsKey(info)) return null;
			String newInfo = (String) prop.get(info);
			if(newInfo.equals("null")) return null;
			
			File f = searchFile(newInfo, c+1);
			if(f==null) return null;
			paths.put(info, f);
			return f;
		}
		
		if(info.startsWith("[")) {
			Matcher m = PATTERN_MOVE.matcher(info);
			if(!m.find()) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			if(m.start()>0) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			
			int move = Integer.parseInt(m.group(1));
			String tail = info.substring(m.end());
			
			File f_tail = searchFile(tail, c+1);
			if(f_tail==null) return null;
			
			File file = moveFile(f_tail, move);
			return guslink(file);
		}

		if(info.startsWith("<")) {
			Matcher m = PATTERN_TAG.matcher(info);
			if(!m.find()) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			if(m.start()>0) throw new Exception("Wrong syntax for path rule: ["+info+"]");
			
			String tag = m.group(1);
			String tail = info.substring(m.end());
			
			File f_tag = searchFile(tag,c+1);
			if(f_tag==null) return null;
			
			File file = (File) pathToFile.t(new Object[] {f_tag, tail});
			return guslink(file);
		}
		
		File file = (File) pathToFile.t(info);
		return guslink(file);
	}
	
	
	
	
	private File moveFile(File file, int move) {
		if(!file.isAbsolute())
			file = file.getAbsoluteFile();
		
		int delta = move<0 ? move*-1 : deep(file)-move;
		
		for(int i=0;i<delta;i++)
			if(file.getParentFile()!=null)
		file = file.getParentFile();
		
		return file;
	}
	
	
	
	
	private int deep(File file) {
		int d = 0;
		while(file.getParentFile()!=null)
		{file = file.getParentFile();d++;}
		return d;
	}
	
	
	private File guslink(File f) throws Exception
	{return (File) handleGusLink.t(f);}
}
