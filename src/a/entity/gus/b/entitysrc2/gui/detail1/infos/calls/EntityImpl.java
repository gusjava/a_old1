package a.entity.gus.b.entitysrc2.gui.detail1.infos.calls;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210920";}

	
	private Service toString;
	
	private JPanel panel;
	private JTextArea area1;
	private JTextArea area2;

	private Object holder;

	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus.b.tostring1.set");
		
		area1 = new JTextArea();
		area1.setMargin(new Insets(3, 3, 3, 3));
		area1.setEditable(false);
		
		area2 = new JTextArea();
		area2.setMargin(new Insets(3, 3, 3, 3));
		area2.setEditable(false);
		
		panel = new JPanel(new GridLayout(2,1));
		panel.add(titled(area1,"Services"));
		panel.add(titled(area2,"Resources"));
	}
	
	
	
	private JPanel titled(JComponent comp, String title)
	{
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(title);
		label.setHorizontalAlignment(JLabel.CENTER);
		panel.add(label,BorderLayout.NORTH);
		panel.add(new JScrollPane(comp),BorderLayout.CENTER);
		return panel;
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		holder = obj;
		
		Set services = (Set) ((R) holder).r("services");
		String s1 = (String) toString.t(services);
		area1.setText(s1);
		
		Set resources = (Set) ((R) holder).r("resources");
		String s2 = (String) toString.t(resources);
		area2.setText(s2);
	}
	
	
	public Object i() throws Exception
	{
		return panel;
	}
	
	
	private void reset() throws Exception
	{
		holder = null;
		area1.setText("");
		area2.setText("");
	}
}
