package a.entity.gus.b.entitysrc2.perform.rename.ask;

import java.awt.Component;
import java.awt.Window;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210908";}

	public static final String TITLE = "Entity rename";
	public static final String MESSAGE = "Please, enter entity's new name:";
	public static final String MESSAGE_ERR = "Entity rename has been aborted";

	public static final String TITLE_REFACTOR = "Entity's dependencies";


	private Service perform;
	private Service findDownLinks;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.b.entitysrc2.perform.rename");
		findDownLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.find2");
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
		
		// find dependencies
		
		Connection cx = (Connection) ((R) engine).r("cx");
		Set downLinks = (Set) findDownLinks.t(new Object[] {cx, oldName});
		boolean refactor = false;
		
		if(!downLinks.isEmpty())
		{
			String message = "The entity "+oldName+" is used by "+downLinks.size()+" other entities:\n"
					+ toString(downLinks) + "\nWould you like to update these links with the new name ?";
			int r = JOptionPane.showConfirmDialog(window, message, TITLE_REFACTOR, JOptionPane.YES_NO_OPTION);
			refactor = r==JOptionPane.YES_OPTION;
		}
		
		// rename entity
		
		boolean done = perform.f(new Object[] {engine, oldName, newName, refactor});
		if(!done)
		{
			JOptionPane.showMessageDialog(window, MESSAGE_ERR, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	
	
	private String toString(Set links)
	{
		StringBuffer b = new StringBuffer();
		List list = new ArrayList(links);
		Collections.sort(list);
		int nb = Math.max(list.size(), 10);
		for(int i=0;i<nb;i++)
			b.append(list.get(i)+"\n");
		if(nb<list.size()) b.append("...\n");
		return b.toString();
	}
}
