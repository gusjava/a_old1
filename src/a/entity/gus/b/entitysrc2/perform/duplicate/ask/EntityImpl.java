package a.entity.gus.b.entitysrc2.perform.duplicate.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210908";}

	public static final String TITLE = "Entity duplication";
	public static final String MESSAGE = "Please, enter entity's new name:";
	public static final String MESSAGE_ERR = "Entity duplication has been aborted";

	
	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.b.entitysrc2.perform.duplicate");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String oldName = (String) o[1];
		Object anchor = o[2];
		
		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		String newName = (String) JOptionPane.showInputDialog(window, MESSAGE, TITLE, JOptionPane.PLAIN_MESSAGE, null, null, oldName);
		if(newName==null || newName.trim().equals("") || newName.equals(oldName)) return false;
		
		boolean done = perform.f(new Object[] {engine, oldName, newName});
		if(!done)
		{
			JOptionPane.showMessageDialog(window, MESSAGE_ERR, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
