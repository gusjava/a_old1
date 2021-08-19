package a.entity.gus.b.timer1.build.delayholder;

import java.util.Timer;
import java.util.TimerTask;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210818";}

	
	private Service executeToTask;

	public EntityImpl() throws Exception {
		executeToTask = Outside.service(this,"gus.a.features.e.totimertask");
	}
	
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Timer timer = (Timer) o[0]; 
		E execute = (E) o[1];
		long lapse = Long.parseLong(""+o[2]);
		
		return new Holder(timer,execute,lapse);
	}
	
	
	private class Holder implements E {
		private Timer timer;
		private E execute;
		private long lapse;
		
		private TimerTask task;
		
		public Holder(Timer timer, E execute, long lapse) {
			this.timer = timer;
			this.execute = execute;
			this.lapse = lapse;
		}
		
		public void e() throws Exception {
			if(task!=null) task.cancel();
			task = (TimerTask) executeToTask.t(execute);
			timer.schedule(task, lapse);
		}
	}
}
