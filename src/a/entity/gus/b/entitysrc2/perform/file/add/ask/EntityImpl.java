package a.entity.gus.b.entitysrc2.perform.file.add.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210909";}

	public static final String TITLE = "Adding new file";
	public static final String MESSAGE = "Please, enter new file's name:";
	public static final String MESSAGE_ERR = "File's creation has been aborted";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.b.entitysrc2.perform.file.add");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		Object anchor = o[2];
		
		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		String className = (String) JOptionPane.showInputDialog(window, MESSAGE, TITLE, JOptionPane.PLAIN_MESSAGE);
		if(className==null || className.trim().equals("")) return false;
		
		className = className.replaceAll("\\s","");
		
		int n = className.length();
		className = className.substring(0,1).toUpperCase() + className.substring(1);
		if(className.endsWith(".java")) className = className.substring(0,n-5);
		
		boolean done = perform.f(new Object[] {engine, entityName, className});
		if(!done)
		{
			JOptionPane.showMessageDialog(window, MESSAGE_ERR, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
