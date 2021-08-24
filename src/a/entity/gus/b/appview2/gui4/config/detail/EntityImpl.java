package a.entity.gus.b.appview2.gui4.config.detail;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl implements Entity, P, I, ListSelectionListener {
	public String creationDate() {return "20210823";}


	private Service listRenderer;
	private Service viewer;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;

	private File srcLocation;
	private List entries;
	private Map map;
	

	public EntityImpl() throws Exception {
		listRenderer = Outside.service(this,"gus.b.swing1.list.cust.renderer.icon.ext");
		viewer = Outside.service(this,"*gus.b.appview1.entryview");

		list = new JList();
		list.addListSelectionListener(this);
		listRenderer.p(list);

		labelNumber = new JLabel(" ");
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(labelNumber,BorderLayout.SOUTH);
		
		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(250);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception {
		if(obj==null) {resetGui();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		srcLocation = (File) o[0];
		entries = (List) o[1];
		
		map = new HashMap();
		int nb = entries.size();
		for(int i=0;i<nb;i++) {
			String entry = (String) entries.get(i);
			String path0 = findPath0(entry);
			map.put(path0, entry);
		}
		
		Vector vec = new Vector(map.keySet());
		Collections.sort(vec);
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
		
		viewer.p(null);
	}
	
	
	private void resetGui() throws Exception {
		list.setListData(new Vector());
		labelNumber.setText(" ");
		viewer.p(null);
	}
	
	
	private String findPath0(String path) {
		String[] nn = path.split("[\\\\\\/]");
		StringBuffer b = new StringBuffer();
		for(int i=6;i<nn.length;i++) {
			b.append(nn[i]);
			if(i<nn.length-1) b.append("/");
		}
		return b.toString();
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    
	

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {viewer.p(null);return;}
			
			String path0 = (String) list.getSelectedValue();
			String entry = (String) map.get(path0);
			
			viewer.p(new Object[] {srcLocation, entry});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
