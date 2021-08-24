package a.entity.gus.b.appview2.gui3.entity.detail;

import java.io.File;
import java.util.List;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210823";}

	private Service shiftPanel;
	private Service panelMany;
	private Service srcViewer;
	

	public EntityImpl() throws Exception {
		shiftPanel = Outside.service(this,"*gus.a.swing.panel.shiftpanel");
		panelMany = Outside.service(this,"*gus.b.appview2.gui3.entity.detail.many");
		srcViewer = Outside.service(this,"*gus.b.appview1.entryview");
	}
	
	public void p(Object obj) throws Exception {
		if(obj==null) {resetGui();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File srcLocation = (File) o[0];
		List entries = (List) o[1];

		if(entries.size()==1) {
			String javaEntry = (String) entries.get(0);
			srcViewer.p(new Object[] {srcLocation, javaEntry});
			shiftPanel.p(srcViewer);
		}
		else {
			panelMany.p(new Object[] {srcLocation, entries});
			shiftPanel.p(panelMany);
		}
	}
	
	private void resetGui() throws Exception {
		shiftPanel.p(null);
	}
	
	public Object i() throws Exception {
		return shiftPanel.i();
	}
}
