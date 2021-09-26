package a.entity.gus.b.entitysrc2.gui.detail1.infos.downlinks;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210920";}


	private Service toString;
	
	private JPanel panel;
	private JTextArea area;

	private Object holder;

	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus.b.tostring1.set");
		
		area = new JTextArea();
		area.setMargin(new Insets(3, 3, 3, 3));
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		holder = obj;
		
		Set links = (Set) ((R) holder).r("downLinks");
		String s = (String) toString.t(links);
		
		area.setText(s);
	}
	
	
	public Object i() throws Exception
	{
		return panel;
	}
	
	
	private void reset() throws Exception
	{
		holder = null;
		area.setText("");
	}
}
