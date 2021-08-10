package a.entity.gus.b.entitysrc1.generator1.ask;

import javax.swing.JOptionPane;

import a.framework.*;

public class EntityImpl implements Entity, E, B {
	public String creationDate() {return "20210810";}


	private Service entityGenerator;
	
	public EntityImpl() throws Exception {
		entityGenerator = Outside.service(this,"gus.b.entitysrc1.generator1");
	}
	
	
	public void e() throws Exception {
		b();
	}
	
	public boolean b() throws Exception {
		String rule = JOptionPane.showInputDialog("Generation rule");
		if(rule==null || rule.equals("")) return false;
		
		entityGenerator.p(rule);
		return true;
	}
}
