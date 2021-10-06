package a.entity.gus.b.entitysrc2.gui.maingui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20210830";}


	private Service engine;
	private Service buildHolder;
	private Service guiListing;
	private Service guiDetail;
	private Service persistGP;
	private Service persistTable;
	private Service persistSet;
	
	private JSplitPane split;
	
	private Object holder;
	

	public EntityImpl() throws Exception {
		engine = Outside.service(this,"gus.b.entitysrc2.engine.main");
		buildHolder = Outside.service(this,"gus.b.entitysrc2.engine.entityholder");
		guiListing = Outside.service(this,"*gus.b.entitysrc2.gui.listing1");
		guiDetail = Outside.service(this,"*gus.b.entitysrc2.gui.detail1");
		persistGP = Outside.service(this,"gus.b.persist1.features.gp");
		persistTable = Outside.service(this,"gus.b.persist1.swing.table.selectedrow");
		persistSet = Outside.service(this,"gus.b.persist1.set.string");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);
		
		split.setLeftComponent((JComponent) guiListing.i());
		split.setRightComponent((JComponent) guiDetail.i());
		
		/*
		 * LOADING GUI LISTING
		 */

		Object fieldHolder = guiListing.r("fieldHolder");
		persistGP.v(getClass().getName()+"_field", fieldHolder);

		Object lockSet = guiListing.r("lockSet");
		persistSet.v(getClass().getName()+"_lockSet", lockSet);
		
		guiListing.p(engine);
		guiListing.addActionListener(this);
		guiListing.e();
		
		SwingUtilities.invokeLater(this::persistTable);
	}
	
	
	private void persistTable()
	{
		try {
			Object table = guiListing.r("table");
			persistTable.v(getClass().getName()+"_table", table);
		}
		catch(Exception e) {
			Outside.err(this, "persistTable()", e);
		}
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	
	private void selectionChanged()
	{
		try
		{
			String selectionPath = (String) guiListing.g();
			if(selectionPath==null) {guiDetail.p(null);return;}
			
			holder = buildHolder.t(new Object[] {selectionPath, engine});
			guiDetail.p(holder);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
