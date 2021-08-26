package a.entity.gus.b.dataview1.object.builder.name;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.I;
import a.framework.P;
import a.framework.R;
import a.framework.T;
import a.framework.V;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210811";}


	public static final String CLASS = "gus.b.dataview1.class1";
	public static final String STRING = "gus.b.dataview1.string";
	public static final String DATE = "gus.b.dataview1.date";

	public static final String MAP = "gus.b.dataview1.map";
	public static final String LIST = "gus.b.dataview1.list";
	public static final String ARRAY = "gus.b.dataview1.array";
	
	public static final String ENTITY = "gus.b.dataview1.entity";
	public static final String E = "gus.b.dataview1.feature.e";
	public static final String I = "gus.b.dataview1.feature.i";
	public static final String G = "gus.b.dataview1.feature.g";
	public static final String T = "gus.b.dataview1.feature.t";
	public static final String F = "gus.b.dataview1.feature.f";
	public static final String P = "gus.b.dataview1.feature.p";
	public static final String R = "gus.b.dataview1.feature.r";
	public static final String V = "gus.b.dataview1.feature.v";
	
	
	public Object t(Object obj) throws Exception {
		Map names = new HashMap();
		
		if(obj instanceof Class) names.put("Class",CLASS);
		
		if(obj instanceof String) names.put("String",STRING);
		if(obj instanceof Date) names.put("Date",DATE);

		if(obj instanceof Map) names.put("Map",MAP);
		if(obj instanceof List) names.put("List",LIST);
		if(obj instanceof Object[]) names.put("Array",ARRAY);

		if(obj instanceof Entity) names.put("Entity",ENTITY);
		if(obj instanceof E) names.put("E",E);
		if(obj instanceof I) names.put("I",I);
		if(obj instanceof G) names.put("G",G);
		if(obj instanceof T) names.put("T",T);
		if(obj instanceof F) names.put("F",F);
		if(obj instanceof P) names.put("P",P);
		if(obj instanceof R) names.put("R",R);
		if(obj instanceof V) names.put("V",V);

		return names;
	}
}
