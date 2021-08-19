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
	private Service mainViewer;
	private Service appViewer;
	private Service generatorGui;
	private Service importGui;
	private Service entitySrcGui;
	
	private JPanel panel;

	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.b.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		mainViewer = Outside.service(this,"*gus.b.dataview1.map.main");
		appViewer = Outside.service(this,"*gus.b.appview1.maingui.self");
		generatorGui = Outside.service(this,"*gus.b.entitysrc1.generator1.multi.gui");
		importGui = Outside.service(this,"*gus.b.entitysrc1.importer1.gus06.gui1");
		entitySrcGui = Outside.service(this,"*gus.b.entitysrc1.listinggui1.main");
		
		tabHolder.v("GUI_main#main", mainViewer);
		tabHolder.v("GUI_app#app", appViewer);
		tabHolder.v("GUI_generator#generator", generatorGui);
		tabHolder.v("GUI_import#import", importGui);
		tabHolder.v("entity#entities", entitySrcGui);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
