package a.entity.gus.b.demo1.panel.grid22;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	
	public String creationDate() {
		return "20210805";
	}
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	
	private JPanel panel;
	
	public EntityImpl() throws Exception {
		gui1 = Outside.service(this,"*gus.a.swing.panel.bg.green-1");
		gui2 = Outside.service(this,"*gus.a.swing.panel.bg.green-2");
		gui3 = Outside.service(this,"*gus.a.swing.panel.bg.green-3");
		gui4 = Outside.service(this,"*gus.a.swing.panel.bg.green-4");
		
		panel = new JPanel(new GridLayout(2,2));
		panel.add((JComponent) gui1.i());
		panel.add((JComponent) gui2.i());
		panel.add((JComponent) gui3.i());
		panel.add((JComponent) gui4.i());
	}

	public Object i() throws Exception {
		return panel;
	}
}
