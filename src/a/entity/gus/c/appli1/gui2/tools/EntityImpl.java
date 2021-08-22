package a.entity.gus.c.appli1.gui2.tools;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210820";}

	private Service tabPersist;
	private Service tabHolder;
	private Service generatorGui;
	private Service importGui;

	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.b.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		
		generatorGui = Outside.service(this,"*gus.b.entitysrc1.generator1.multi.gui");
		importGui = Outside.service(this,"*gus.b.entitysrc1.importer1.gus06.gui1");

		tabHolder.v("GUI_generator#generator", generatorGui);
		tabHolder.v("GUI_import#import", importGui);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
