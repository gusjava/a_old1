package a.entity.gus.b.gyem1.watcher.icons;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210828";}

	
	
	private JPanel panel;
	private JLabel labelErr;
	private JLabel labelEntityDef;
	private JLabel labelEntityClass;
	private JLabel labelEntityUnique;

	public EntityImpl() throws Exception {
		
		
		labelErr = new JLabel(" ");
		labelEntityDef = new JLabel(" ");
		labelEntityClass = new JLabel(" ");
		labelEntityUnique = new JLabel(" ");
		
		panel = new JPanel(new GridLayout(1,4));
		panel.add(labelErr);
		panel.add(labelEntityDef);
		panel.add(labelEntityClass);
		panel.add(labelEntityUnique);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
}
