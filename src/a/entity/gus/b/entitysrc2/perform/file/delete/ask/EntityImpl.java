package a.entity.gus.b.entitysrc2.perform.file.delete.ask;

import java.awt.Component;
import java.awt.Window;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210909";}

	public static final String TITLE = "File deletion";
	public static final String MESSAGE = "Please, confirm deletion for file: ";

	public static final String TITLE_DEPENDENCIES = "Entity's dependencies";


	private Service perform;
	private Service findDownLinks;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.b.entitysrc2.perform.file.delete");
		findDownLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.find2");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		String className = (String) o[2];
		Object anchor = o[3];
		
		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		
		{
			String message = MESSAGE+"\n"+className;
			int r = JOptionPane.showConfirmDialog(window, message, TITLE, JOptionPane.YES_NO_OPTION);
			if(r!=JOptionPane.YES_OPTION) return false;
		}
		// find dependencies
		
		Connection cx = (Connection) ((R) engine).r("cx");
		Set downLinks = (Set) findDownLinks.t(new Object[] {cx, entityName});
		
		if(!downLinks.isEmpty())
		{
			String message = "The entity "+entityName+" is used by "+downLinks.size()+" other entities:\n"
					+ toString(downLinks) + "\nAre you really sure you want to delete this entity ?";
			int r = JOptionPane.showConfirmDialog(window, message, TITLE_DEPENDENCIES, JOptionPane.YES_NO_OPTION);
			if(r!=JOptionPane.YES_OPTION) return false;
		}
		
		// delete entity
		
		return perform.f(new Object[] {engine, entityName, className});
	}
	
	
	
	private String toString(Set links)
	{
		StringBuffer b = new StringBuffer();
		List list = new ArrayList(links);
		Collections.sort(list);
		int nb = Math.min(list.size(), 10);
		for(int i=0;i<nb;i++)
			b.append("- "+list.get(i)+"\n");
		if(nb<list.size()) b.append("- ...\n");
		return b.toString();
	}
}
