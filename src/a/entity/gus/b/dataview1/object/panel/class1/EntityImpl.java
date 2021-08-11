package a.entity.gus.b.dataview1.object.panel.class1;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.P;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210811";}

	
	private JPanel panel;

	public EntityImpl() throws Exception {
		panel = new JPanel();
	}
	
	
	public void p(Object obj) throws Exception {
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
}
