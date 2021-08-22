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

	private Service tabPersist;
	private Service tabHolder;
	private Service monitorGui;
	private Service toolsGui;
	private Service entitiesGui;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.b.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		
		monitorGui = Outside.service(this,"*gus.c.appli1.gui1.monitor");
		toolsGui = Outside.service(this,"*gus.c.appli1.gui2.tools");
		entitiesGui = Outside.service(this,"*gus.b.entitysrc1.listinggui1.main");
		
		tabHolder.v("GUI_monitor#Runtime", monitorGui);
		tabHolder.v("GUI_tools#Tools", toolsGui);
		tabHolder.v("entity#Entities", entitiesGui);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
