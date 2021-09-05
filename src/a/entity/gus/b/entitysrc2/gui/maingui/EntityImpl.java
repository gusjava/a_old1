package a.entity.gus.b.entitysrc2.gui.maingui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20210830";}


	private Service engine;
	private Service guiListing;
	private Service guiDetail;
	private Service persistTextComp;
	private Service persistSet;
	
	private JSplitPane split;

	public EntityImpl() throws Exception {
		engine = Outside.service(this,"gus.b.entitysrc2.engine.main");
		guiListing = Outside.service(this,"*gus.b.entitysrc2.gui.listing1");
		guiDetail = Outside.service(this,"*gus.b.entitysrc2.gui.detail1");
		persistTextComp = Outside.service(this,"gus.b.persist1.swing.textcomp");
		persistSet = Outside.service(this,"gus.b.persist1.set.string");
		
		Object field = guiListing.r("field");
		persistTextComp.v(getClass().getName()+"_field", field);

		Object lockSet = guiListing.r("lockSet");
		persistSet.v(getClass().getName()+"_lockSet", lockSet);
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);
		
		split.setLeftComponent((JComponent) guiListing.i());
		split.setRightComponent((JComponent) guiDetail.i());
		
		guiListing.p(engine);
		
		guiListing.addActionListener(this);
	}
	
	
	public Object i() throws Exception {
		return split;
	}


	public void actionPerformed(ActionEvent e) {
		selectionChanged();
	}
	
	
	
	private void selectionChanged() {
		try {
			String entityName = (String) guiListing.g();
			if(entityName==null) {guiDetail.p(null);return;}
			
			guiDetail.p(new Object[] {entityName, engine});
		}
		catch(Exception e) {
			Outside.err(this,"selectionChanged()",e);
		}
	}
}
