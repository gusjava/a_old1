package a.entity.gus.b.entitysrc1.importer1.gus06.gui1;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, I, P {
	public String creationDate() {return "20210814";}


	private Service gui;
	private Service persister;
	private Service engine;

	private JPanel panel;
	private JTextField fieldDir;

	public EntityImpl() throws Exception {
		gui = Outside.service(this,"*gus.a.features.p.string.inputgui1");
		persister = Outside.service(this,"gus.b.persist1.swing.textcomp");
		engine = Outside.service(this,"gus.b.entitysrc1.importer1.gus06.engine1");
		
		fieldDir = new JTextField();
		
		persister.v(getClass().getName()+"_field", fieldDir);
		
		panel = new JPanel(new BorderLayout());
		panel.add(fieldDir, BorderLayout.NORTH);
		panel.add((JComponent) gui.i(), BorderLayout.CENTER);

		gui.p(this);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
	
	
	public void p(Object obj) throws Exception {
		String input = (String) obj;
		
		File dir = new File(fieldDir.getText());
		if(!dir.isDirectory()) throw new Exception("Root directory not found: "+dir);
		
		engine.p(new Object[] {dir,input});
	}
}
