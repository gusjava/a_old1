package a.entity.gus.b.dataview1.feature.p;

import javax.swing.JLabel;

import a.framework.*;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210811";}


	private JLabel label;
	
	private P data;

	public EntityImpl() throws Exception {
		label = new JLabel("PENDING ...");
	}
	
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public void p(Object obj) throws Exception {
		data = (P) obj;
	}
	
	
	public Object i() throws Exception {
		return label;
	}
}
