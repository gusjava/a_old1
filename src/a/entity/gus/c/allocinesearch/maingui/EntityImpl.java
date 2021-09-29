package a.entity.gus.c.allocinesearch.maingui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210814";}


	private JPanel panel;
	
	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception 
	{
		return panel;
	}
}
