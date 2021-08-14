package a.entity.gus.b.ling1.lib.load;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, R {
	public String creationDate() {return "20210814";}

	
	private Service loadProp;

	public EntityImpl() throws Exception {
		loadProp = Outside.service(this,"m005.t.config.loc.load.prop");
	}
	
	public Object r(String key) throws Exception {
		String configLoc = "ling/" + key;
		return loadProp.t(configLoc);
	}
}
