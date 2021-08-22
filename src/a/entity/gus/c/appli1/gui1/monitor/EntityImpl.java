package a.entity.gus.c.appli1.gui1.monitor;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210820";}

	private Service tabPersist;
	private Service tabHolder;
	
	private Service mainViewer;
	private Service appViewer;

	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.b.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		
		mainViewer = Outside.service(this,"*gus.b.dataview1.map.main");
		appViewer = Outside.service(this,"*gus.b.appview1.maingui.self");
		
		tabHolder.v("GUI_main#main", mainViewer);
		tabHolder.v("GUI_app#app", appViewer);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
