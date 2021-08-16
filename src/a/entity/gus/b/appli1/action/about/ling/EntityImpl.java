package a.entity.gus.b.appli1.action.about.ling;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, E, G {

	public String creationDate() {return "20210816";}

	public static final String DISPLAY = "ACTION_about#About";



	private Service execute;
	private Service buildAction;
	
	private Action action;
	
	
	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus.b.appli1.execute.about");
		buildAction = Outside.service(this,"gus.b.actions1.ling.builder1");
		
		action = (Action) buildAction.t(new Object[]{DISPLAY,this}); 
	}
	
	
		
	public Object g() throws Exception
	{return action;}
	
	public void e() throws Exception
	{execute.e();}
}