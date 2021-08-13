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

	private Service tabHolder;
	private Service mainViewer;
	private Service appViewer;
	
	private JPanel panel;

	public EntityImpl() throws Exception {
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		mainViewer = Outside.service(this,"*gus.b.dataview1.map.main");
		appViewer = Outside.service(this,"*gus.b.appview1.maingui.self");
		
		tabHolder.v("GUI_main#main", mainViewer);
		tabHolder.v("GUI_app#app", appViewer);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
