package a.entity.gus.b.entitysrc2.gui.maingui;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210830";}


	private Service engine;
	private Service guiListing;
	
	private JSplitPane split;

	public EntityImpl() throws Exception {
		engine = Outside.service(this,"gus.b.entitysrc2.engine.main");
		guiListing = Outside.service(this,"*gus.b.entitysrc2.gui.listing1");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);
		
		split.setLeftComponent((JComponent) guiListing.i());
		
		Object data = engine.g();
		guiListing.p(data);
	}
	
	
	public Object i() throws Exception {
		return split;
	}
}
