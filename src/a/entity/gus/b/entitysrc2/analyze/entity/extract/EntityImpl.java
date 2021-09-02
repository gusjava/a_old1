package a.entity.gus.b.entitysrc2.analyze.entity.extract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210830";}
	
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_FEATURES = "features";
	public static final String KEY_CREATIONDATE = "creationdate";
	public static final String KEY_RESOURCES = "resources";
	public static final String KEY_SERVICES = "services";

	

	private Service toArray;

	public EntityImpl() throws Exception {
		toArray = Outside.service(this,"gus.a.string.toarray.javasrc");
	}
	
	
	public Object t(Object obj) throws Exception {
		String[] lines = (String[]) toArray.t(obj);
		
		Map data = new HashMap();
		put(data, KEY_PACKAGE, extractPackage(lines));
		put(data, KEY_FEATURES, extractFeatures(lines));
		put(data, KEY_CREATIONDATE, extractCreationDate(lines));
		put(data, KEY_RESOURCES, extractResources(lines));
		put(data, KEY_SERVICES, extractServices(lines));
		
		return data;
	}
	
	
	private void put(Map data, String key, Object value) {
		if(value!=null) data.put(key, value);
	}
	

	/*
	 * PACKAGE
	 */

	public static final Pattern P_PACKAGE = Pattern.compile("package +([^;]+);");
	
	private String extractPackage(String[] lines) throws Exception {
		for(String line:lines)
		if(line.startsWith("package ")) {
			Matcher m = P_PACKAGE.matcher(line);
			if(!m.find()) throw new Exception("Package extraction failed for line: "+line);
			return m.group(1);
		}
		return null;
	}
	
	/*
	 * FEATURES
	 */
	
	private String extractFeatures(String[] lines) throws Exception
	{
		for(String line:lines)
		if(line.startsWith("public class EntityImpl "))
		{
			StringBuffer b = new StringBuffer();
			
			boolean extendsS1 = line.contains(" extends S1 ");
			String[] n = line.split(" implements ");
			if(n.length!=2) throw new Exception("Invalid entity class header: "+line);
			
			String part = " "+n[1].replace(","," ")+" ";

			if(part.contains(" B ")) b.append("b");
			if(part.contains(" E ")) b.append("e");
			if(part.contains(" F ")) b.append("f");
			if(part.contains(" G ")) b.append("g");
			if(part.contains(" H ")) b.append("h");
			if(part.contains(" I ")) b.append("i");
			if(part.contains(" P ")) b.append("p");
			if(part.contains(" R ")) b.append("r");
			if(extendsS1 || part.contains(" S ")) b.append("s");
			if(part.contains(" T ")) b.append("t");
			if(part.contains(" V ")) b.append("v");
			return b.toString();
		}
		return null;
	}
	
	/*
	 * CREATION DATE
	 */

	public static final Pattern P_CREATIONDATE = Pattern.compile("\"([^\"]+)\"");
	
	private String extractCreationDate(String[] lines) throws Exception
	{
		for(int i=0;i<lines.length;i++)
		if(lines[i].startsWith("public String creationDate()")) {
			Matcher m = P_CREATIONDATE.matcher(lines[i]);
			if(m.find()) return m.group(1);
			
			m = P_CREATIONDATE.matcher(lines[i+1]);
			if(m.find()) return m.group(1);
			throw new Exception("CreationDate extraction failed for line: "+lines[i+1]);
		}
		return null;
	}
	
	/*
	 * RESOURCES
	 */

	public static final Pattern P_RESOURCES = Pattern.compile("\"([^\"]+)\"");
	
	private List extractResources(String[] lines) throws Exception
	{
		List list = new ArrayList();
		for(String line:lines)
		if(line.contains("Outside.resource(")) {
			Matcher m = P_RESOURCES.matcher(line);
			if(m.find()) list.add(m.group(1));
		}
		return list;
	}
	
	/*
	 * SERVICES
	 */

	public static final Pattern P_SERVICES = Pattern.compile("\"([^\"]+)\"");
	
	private List extractServices(String[] lines) throws Exception
	{
		List list = new ArrayList();
		for(String line:lines)
		if(line.contains("Outside.service(")) {
			Matcher m = P_SERVICES.matcher(line);
			if(m.find()) list.add(m.group(1));
		}
		return list;
	}
}
