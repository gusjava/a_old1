package a.entity.gus.b.timer1.register;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import a.framework.*;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20210814";}

	
	private Service executeToTask;

	private Timer timer;
	
	public EntityImpl() throws Exception {
		executeToTask = Outside.service(this,"gus.a.features.e.totimertask");
		timer = new Timer("TIMER_"+getClass().getName());
	}
	
	
	public void v(String key, Object obj) throws Exception {
		TimerTask task = (TimerTask) executeToTask.t(obj);
		long lapse = Long.parseLong(key);
		timer.schedule(task,new Date(),lapse);
	}
}
