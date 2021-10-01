package a.entity.gus.b.entitysrc2.gui.detail1.src;

import java.io.File;
import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210907";}


	private Service shiftPanel;
	private Service panelSingle;
	private Service panelMany;
	
	private Object holder;
	
	
	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.a.swing.panel.shiftpanel");
		panelSingle = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.src.single");
		panelMany = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.src.many");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		holder = obj;
		
		File[] javaFiles = (File[]) ((R) holder).r("javaFiles");
		if(javaFiles==null) {
			reset();
		}
		else if(javaFiles.length==1) {
			panelSingle.p(holder);
			shiftPanel.p(panelSingle);
		}
		else {
			panelMany.p(holder);
			shiftPanel.p(panelMany);
		}
	}
	
	
	private void reset() throws Exception
	{
		holder = null;
		panelSingle.p(null);
		panelMany.p(null);
		shiftPanel.p(null);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
}
