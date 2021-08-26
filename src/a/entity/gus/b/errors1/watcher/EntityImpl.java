package a.entity.gus.b.errors1.watcher;

import java.util.List;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl extends S1 implements Entity, G {
	public String creationDate() {return "20210826";}


	public static final long LAPSE = 1500;

	private Service timer;
	private List errList;
	private int nb = 0;

	public EntityImpl() throws Exception {
		timer = Outside.service(this,"gus.b.timer1.register");
		errList = (List) Outside.resource(this,"errlist");
		
		timer.v(""+LAPSE, (E) this::check);
	}
	
	
	public Object g() throws Exception
	{return errList;}
	
	
	private void check()
	{
		int nb1 = errList.size();
		if(nb1==nb) return;
			
		nb = nb1;
		detected();
	}
	
	private void detected()
	{send(this,"detected()");}
}
