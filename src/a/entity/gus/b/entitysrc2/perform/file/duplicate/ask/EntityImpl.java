package a.entity.gus.b.entitysrc2.perform.file.duplicate.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210909";}

	public static final String TITLE = "File duplication";
	public static final String MESSAGE = "Please, enter file's new name:";
	public static final String MESSAGE_ERR = "File duplication has been aborted";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.b.entitysrc2.perform.file.duplicate");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		String oldName = (String) o[2];
		Object anchor = o[3];
		
		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		String newName = (String) JOptionPane.showInputDialog(window, MESSAGE, TITLE, JOptionPane.PLAIN_MESSAGE, null, null, oldName);
		if(newName==null || newName.trim().equals("") || newName.equals(oldName)) return false;
		
		newName = newName.replaceAll("\\s","");
		
		int n = newName.length();
		newName = newName.substring(0,1).toUpperCase() + newName.substring(1);
		if(newName.endsWith(".java")) newName = newName.substring(0,n-5);
		
		
		boolean done = perform.f(new Object[] {engine, entityName, oldName, newName});
		if(!done)
		{
			JOptionPane.showMessageDialog(window, MESSAGE_ERR, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
