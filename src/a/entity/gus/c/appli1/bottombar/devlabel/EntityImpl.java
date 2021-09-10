package a.entity.gus.c.appli1.bottombar.devlabel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210910";}


	private String devId;
	private Icon devIcon;
	
	private JLabel label;

	public EntityImpl() throws Exception
	{
		devId = (String) Outside.resource(this, "param#dev");
		devIcon = (Icon) Outside.resource(this,"icon#ELEMENT_dev");
		
		label = new JLabel(devId);
		label.setIcon(devIcon);
		label.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	}
	
	
	public Object i() throws Exception
	{
		return label;
	}
}
