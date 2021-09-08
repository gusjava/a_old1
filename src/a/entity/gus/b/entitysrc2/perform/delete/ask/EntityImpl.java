package a.entity.gus.b.entitysrc2.perform.delete.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210908";}

	public static final String TITLE = "Entity deletion";
	public static final String MESSAGE = "Please, confirm deletion for entity: ";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.b.entitysrc2.perform.delete");
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
		
		String message = MESSAGE+"\n"+entityName;
		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		int r = JOptionPane.showConfirmDialog(window, message, TITLE, JOptionPane.PLAIN_MESSAGE);
		if(r!=JOptionPane.YES_OPTION) return false;
		
		return perform.f(new Object[] {engine, entityName});
	}
}
