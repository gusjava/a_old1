package a.entity.gus.b.actions1.builder0;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210828";}


	private Service exeToAction;
	private Service repaint;

	public EntityImpl() throws Exception
	{
		exeToAction = Outside.service(this,"gus.b.convert1.executetoaction");
		repaint = Outside.service(this,"gus.b.actions1.display");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String display = (String) o[0];
		E execute = (E) o[1];
		
		Action action = (Action) exeToAction.t(execute);
		repaint.v(display, action);
		
		return action;
	}
}
