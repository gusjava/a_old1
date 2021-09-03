package a.entity.gus.b.entitysrc2.gui.detail1;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210903";}

	
	private JPanel panel;
	private JLabel labelTitle;
	private Icon entityIcon;
	
	private String entityName;
	private File rootDir;
	

	public EntityImpl() throws Exception {
		entityIcon = (Icon) Outside.resource(this,"icon#ELEMENT_entity");
		
		labelTitle = new JLabel(" ");
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
	}
	
	
	public void p(Object obj) throws Exception {
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		rootDir = (File) o[1];
		
		labelTitle.setText(entityName);
		labelTitle.setIcon(entityIcon);
	}
	
	
	private void reset() {
		entityName = null;
		rootDir = null;
		
		labelTitle.setText(" ");
		labelTitle.setIcon(null);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
}
