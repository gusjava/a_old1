package a.entity.gus.a.debug.dialog.pending;

import javax.swing.JOptionPane;

import a.framework.E;
import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, E, P {
	public String creationDate() {return "20210908";}


	public static final int TYPE = JOptionPane.WARNING_MESSAGE;
	public static final String MESSAGE = "Pending functionality";
	public static final String TITLE = "Pending functionality";
	
	
	public void e() throws Exception
	{
		show(MESSAGE, TITLE);
	}
	
	public void p(Object obj) throws Exception
	{
		show(MESSAGE+": \n"+obj, TITLE);
	}
	
	private void show(String message, String title)
	{
		JOptionPane.showMessageDialog(null, message, title, TYPE);
	}
}
