package a.core.gus.gyem.m038.t.resource.findrule;

import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.Entity;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] call = (Object[]) obj;
		
		Entity entity = (Entity) call[0];
		String id =  (String) call[1];

		String entityName = (String) moduleT(M045_T_ENTITY_FINDNAME).t(entity);

		if(id==null) throw new Exception("Invalid call from entity ["+entityName+"]: null id");
		if(id.equals("")) throw new Exception("Invalid call from entity ["+entityName+"]: empty id");
		
		Map mapping = (Map) moduleG(M048_G_MAPPING).g();
		String rule = (String) moduleT(M039_T_RESOURCE_FINDRULE1).t(new Object[]{mapping, entityName, id});
		
		if(mapping.containsKey(rule)) rule = (String) mapping.get(rule);
		
		if(rule.equals(""))  throw new Exception("Empty mapping rule found for call ["+entityName+"@"+id+"]");
		return rule;
	}
}
