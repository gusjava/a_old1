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
	private Service bottomBar;
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service entitiesGui;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.b.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		bottomBar = Outside.service(this,"*gus.c.appli1.bottombar");
		
		gui1 = Outside.service(this,"*gus.c.appli1.gui1.monitor");
		gui2 = Outside.service(this,"*gus.c.appli1.gui2.tools");
		gui3 = Outside.service(this,"*gus.c.appli1.gui3.content");
		entitiesGui = Outside.service(this,"*gus.b.entitysrc1.listinggui1.main");
		
		tabHolder.v("GUI_monitor#Runtime", gui1);
		tabHolder.v("GUI_tools#Tools", gui2);
		tabHolder.v("FILE_jar_search#Content", gui3);
		tabHolder.v("ELEMENT_entity#Entities", entitiesGui);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		panel.add((JComponent) bottomBar.i(), BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
