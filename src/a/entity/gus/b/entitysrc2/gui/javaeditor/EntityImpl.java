package a.entity.gus.b.entitysrc2.gui.javaeditor;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210907";}

	
	private Service read;
	
	private JPanel panel;
	private JTextArea area;

	private String entityName;
	private Object engine;
	private File javaFile;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.a.file.string.read");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3, 3, 3, 3));
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		engine = o[1];
		javaFile = (File) o[2];
		
		String text = (String) read.t(javaFile);
		area.setText(text);
		area.setCaretPosition(0);
	}
	
	
	
	private void reset() throws Exception
	{
		entityName = null;
		engine = null;
		javaFile = null;
		
		area.setText("");
	}
	
	
	public Object i() throws Exception
	{
		return panel;
	}
}
