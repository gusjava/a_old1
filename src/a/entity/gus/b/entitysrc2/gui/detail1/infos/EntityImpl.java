package a.entity.gus.b.entitysrc2.gui.detail1.infos;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210907";}


	private JPanel panel;
	
	private String entityName;
	private Object engine;
	
	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		engine = o[1];
	}
	
	private void reset()
	{
		entityName = null;
		engine = null;
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
