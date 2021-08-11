package a.entity.gus.b.dataview1.object;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210811";}


	private Service builder;
	private Service infoViewer;
	private Service classViewer;

	private JTabbedPane tabbedPane;
	private Object data;

	public EntityImpl() throws Exception {
		builder = Outside.service(this,"gus.b.dataview1.object.builder");
		infoViewer = Outside.service(this,"*gus.b.dataview1.object.panel.info");
		classViewer = Outside.service(this,"*gus.b.dataview1.object.panel.class1");
		
		tabbedPane = new JTabbedPane();
	}
	
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public void p(Object obj) throws Exception {
		data = obj;
		tabbedPane.removeAll();
		if(data!=null) initViewerObj();
		else initViewerNull();
	}
	
	
	public Object i() throws Exception {
		return tabbedPane;
	}
	
	
	
	private void initViewerObj() throws Exception {
		Map viewers = (Map) builder.t(data);
		if(viewers!=null) {
			String title = "VIEWERS ("+viewers.size()+")";
			tabbedPane.addTab(title, viewersTab(viewers));
		}
		infoViewer.p(data);
		classViewer.p(data);
				
		tabbedPane.addTab("INFOS", (JComponent) infoViewer.i());
		tabbedPane.addTab("CLASS", (JComponent) classViewer.i());
	}
	
	
	
	private void initViewerNull() throws Exception {
		tabbedPane.addTab("NULL", new JPanel());
	}
	
	
	private JTabbedPane viewersTab(Map viewers) throws Exception {
		JTabbedPane tab = new JTabbedPane();
		ArrayList keys = new ArrayList(viewers.keySet());
		for(int i=0;i<keys.size();i++) {
			String key = (String) keys.get(i);
			I viewer = (I) viewers.get(key);
			tab.addTab(key,(JComponent) viewer.i());
		}
		return tab;
	}
}
