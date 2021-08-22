package a.entity.gus.c.appli1.gui3.content;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210822";}


	private Service tabPersist;
	private Service tabHolder;
	
	private Service appViewer1;
	private Service appViewer2;

	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.b.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		
		appViewer1 = Outside.service(this,"*gus.b.appview1.maingui.self");
		appViewer2 = Outside.service(this,"*gus.b.appview2.maingui.self");
		
		tabHolder.v("GUI_app1#raw", appViewer1);
		tabHolder.v("STRUCT#struct", appViewer2);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
