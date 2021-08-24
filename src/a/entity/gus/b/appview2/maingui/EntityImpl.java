package a.entity.gus.b.appview2.maingui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I, ListSelectionListener {
	public String creationDate() {return "20210822";}


	private Service buildLists;
	private Service listRenderer;
	private Service getIcon;
	private Service shift;
	
	private JPanel panel;
	private JList list;
	private JLabel labelTitle;
	
	private File appLocation;
	private List[] lists;
	private Service[] guis;
	

	public EntityImpl() throws Exception {
		buildLists = Outside.service(this,"gus.b.appview2.build.lists");
		listRenderer = Outside.service(this,"gus.b.swing1.list.cust.renderer.display");
		getIcon = Outside.service(this,"gus.b.files1.icon");
		shift = Outside.service(this,"*gus.a.swing.panel.shiftpanel");
		
		guis = new Service[4];
		guis[0] = Outside.service(this,"*gus.b.appview2.gui1.framework");
		guis[1] = Outside.service(this,"*gus.b.appview2.gui2.core");
		guis[2] = Outside.service(this,"*gus.b.appview2.gui3.entity");
		guis[3] = Outside.service(this,"*gus.b.appview2.gui4.config");
		
		String[] displays = new String[4];
		displays[0] = "STRUCT_framework#framework";
		displays[1] = "STRUCT_core#core";
		displays[2] = "STRUCT_entity#entity";
		displays[3] = "STRUCT_config#config";

		list = new JList();
		list.addListSelectionListener(this);
		listRenderer.p(list);
		
		list.setListData(displays);
        
		labelTitle = new JLabel(" ");
        
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		
		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(100);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) shift.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add(split, BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
	
	
	public Object g() throws Exception {
		return appLocation;
	}
	
	
	public void p(Object obj) throws Exception {
		appLocation = (File) obj;
		
		if(appLocation==null || !appLocation.exists()) resetGui();
		else updateGui();
	}
	
	
	private void resetGui() throws Exception {
		lists = null;
		labelTitle.setText(" ");
		labelTitle.setIcon(null);
		shift.p(null);
	}
	
	
	private void updateGui() throws Exception {
		lists = (List[]) buildLists.t(appLocation);
		shift.p(null);
		
		labelTitle.setText(appLocation.getAbsolutePath());
		labelTitle.setIcon((Icon) getIcon.t(appLocation));
	}


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    
	

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {shift.p(null);return;}
			if(lists==null) {shift.p(null);return;}
			
			int index = list.getSelectedIndex();
			guis[index].p(new Object[] {appLocation, lists[index]});
			shift.p(guis[index]);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
