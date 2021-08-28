package a.entity.gus.b.gyem1.watcher;

import java.util.List;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl implements Entity, R {
	public String creationDate() {return "20210828";}

	public static final long LAPSE = 1500;

	private Service timer;

	private List listErr;
	private Map mapEntityLoaded;
	private Map mapEntityUnique;

	private int nbErr = 0;
	private int nbEntityLoaded = 0;
	private int nbEntityUnique = 0;

	private S1 supportErr = new S1();
	private S1 supportEntityLoaded = new S1();
	private S1 supportEntityUnique = new S1();

	public EntityImpl() throws Exception {
		timer = Outside.service(this,"gus.b.timer1.register");

		listErr = (List) Outside.resource(this,"errlist");
		mapEntityLoaded = (Map) Outside.resource(this, "g#m018.t.entity.findclass");
		mapEntityUnique = (Map) Outside.resource(this, "g#m016.t.entity.unique");

		timer.v(""+LAPSE, (E) this::check);
	}
	
	
	
	private void check()
	{
		int nbErr1 = listErr.size();
		if(nbErr != nbErr1)
		{
			supportErr.send(this,"changed()");
			nbErr = nbErr1;
		}
		int nbEntityClass1 = mapEntityLoaded.size();
		if(nbEntityLoaded != nbEntityClass1)
		{
			supportEntityLoaded.send(this,"changed()");
			nbEntityLoaded = nbEntityClass1;
		}
			
		int nbEntityUnique1 = mapEntityUnique.size();
		if(nbEntityUnique != nbEntityUnique1)
		{
			supportEntityUnique.send(this,"changed()");
			nbEntityUnique = nbEntityUnique1;
		}
	}
	
	
	public Object r(String key) throws Exception {
		if(key.equals("supportErr")) return supportErr;
		if(key.equals("supportEntityLoaded")) return supportEntityLoaded;
		if(key.equals("supportEntityUnique")) return supportEntityUnique;
		
		if(key.equals("keys")) return new String[] {
				"supportErr",
				"supportEntityLoaded",
				"supportEntityUnique"};
		
		throw new Exception("Unknown key: "+key);
	}
}
