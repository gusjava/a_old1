package a.entity.gus.b.dataview1.object.panel.info;

import javax.swing.JPanel;

import a.framework.*;

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
