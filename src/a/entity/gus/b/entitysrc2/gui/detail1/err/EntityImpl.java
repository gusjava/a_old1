package a.entity.gus.b.entitysrc2.gui.detail1.err;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.P;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20211004";}


	private JPanel panel;

	private Object holder;
	
	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		holder = obj;
	}
	
	private void reset()
	{
		holder = null;
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
