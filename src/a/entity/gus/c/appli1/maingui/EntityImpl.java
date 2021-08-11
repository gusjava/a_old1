package a.entity.gus.c.appli1.maingui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210807";}

	private Service mainViewer;
	
	private JPanel panel;

	public EntityImpl() throws Exception {
		mainViewer = Outside.service(this,"*gus.b.dataview1.map.main");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) mainViewer.i(), BorderLayout.CENTER);
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
