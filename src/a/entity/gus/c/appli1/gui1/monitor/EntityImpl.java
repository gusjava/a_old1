package a.entity.gus.c.appli1.gui1.monitor;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210820";}

	private Service tabPersist;
	private Service tabHolder;
	
	private Service mainViewer;
	private Service envPropViewer;
	private Service classPathViewer;

	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.b.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		
		mainViewer = Outside.service(this,"*gus.b.dataview1.map.main");
		envPropViewer = Outside.service(this,"*gus.a.jre.system.envprop.gui1");
		classPathViewer = Outside.service(this,"*gus.b.jre.prop.classpath.view1");
		
		tabHolder.v("GUI_main#main", mainViewer);
		tabHolder.v("FILE_properties#prop/env", envPropViewer);
		tabHolder.v("GUI_classpath#classpath", classPathViewer);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
