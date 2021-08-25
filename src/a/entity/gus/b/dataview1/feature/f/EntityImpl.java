package a.entity.gus.b.dataview1.feature.f;

import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.P;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210811";}

	
	private JLabel label;

	public EntityImpl() throws Exception {
		label = new JLabel("PENDING ...");
	}
	
	
	public Object g() throws Exception {
		return null;
	}
	
	
	public void p(Object obj) throws Exception {
		
	}
	
	
	public Object i() throws Exception {
		return label;
	}
}
