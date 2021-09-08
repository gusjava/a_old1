package a.entity.gus.b.entitysrc2.perform.create.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210908";}

	public static final String TITLE = "Entity creation";
	public static final String MESSAGE = "Please, enter entity's generation rule:";
	public static final String MESSAGE_ERR = "Entity generation has been aborted";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.b.entitysrc2.perform.create");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Object anchor = o[1];
		
		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		String rule = (String) JOptionPane.showInputDialog(window, MESSAGE, TITLE, JOptionPane.PLAIN_MESSAGE);
		if(rule==null || rule.trim().equals("")) return false;
		
		boolean done = perform.f(new Object[] {engine, rule});
		if(!done)
		{
			JOptionPane.showMessageDialog(window, MESSAGE_ERR, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
