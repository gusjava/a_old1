package a.entity.gus.b.timer1.register;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import a.framework.*;

public class EntityImpl implements Entity, V, T {
	public String creationDate() {return "20210814";}

	
	private Service executeToTask;
	private Service buildDelayHolder;

	private Timer timer;
	
	public EntityImpl() throws Exception {
		executeToTask = Outside.service(this,"gus.a.features.e.totimertask");
		buildDelayHolder = Outside.service(this,"gus.b.timer1.build.delayholder");
		
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	
	public void v(String key, Object obj) throws Exception {
		TimerTask task = (TimerTask) executeToTask.t(obj);
		long lapse = Long.parseLong(key);
		
		timer.schedule(task,new Date(), lapse);
	}
	

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object lapse = o[0];
		E execute = (E) o[1];
		
		return buildDelayHolder.t(new Object[] {timer, execute, lapse});
	}
}
