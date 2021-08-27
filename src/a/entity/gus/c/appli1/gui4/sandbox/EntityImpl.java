package a.entity.gus.c.appli1.gui4.sandbox;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210827";}

	
	private JPanel panel;

	public EntityImpl() throws Exception {
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
}
