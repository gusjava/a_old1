package a.entity.gus.b.dataview1.map;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I, ListSelectionListener {
	public String creationDate() {return "20210811";}

	
	private Service viewer;
	private Service splitCust;
	private Service listRenderer;

	private JSplitPane split;
	private JList list;
	private JLabel labelNumber;
	private JLabel labelTitle;

	private Map data;
	

	public EntityImpl() throws Exception {
		viewer = Outside.service(this,"*gus.b.dataview1.object");
		splitCust = Outside.service(this,"gus.a.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this,"gus.a.swing.list.cust.renderer.obj");
		
		list = new JList();
		list.addListSelectionListener(this);
		listRenderer.p(list);

		labelNumber = new JLabel(" ");
		labelTitle = new JLabel(" ");
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(new JScrollPane(list),BorderLayout.CENTER);
		panel1.add(labelNumber,BorderLayout.SOUTH);
		
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(labelTitle,BorderLayout.NORTH);
		panel2.add((JComponent) viewer.i(),BorderLayout.CENTER);
		
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(panel1);
		split.setRightComponent(panel2);
	}
	
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public void p(Object obj) throws Exception {
		data = (Map) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	
	public Object i() throws Exception {
		return split;
	}
	
	
	
	private void updateGui() {
		SwingUtilities.invokeLater((Runnable) this::updateGui_);
	}
	
	
	
	private void updateGui_()
	{
		try {
			synchronized(data) {
				Vector vec = new Vector(data.keySet());
				Collections.sort(vec);
				list.setListData(vec);
			}
					
			labelNumber.setText(" "+data.size());
			viewer.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui_()",e);}
	}
	
	
	
	private void resetGui() throws Exception {
		list.setListData(new Vector());
		labelNumber.setText(" ");
		labelTitle.setText(" ");
		viewer.p(null);
	}
		
	
	public void valueChanged(ListSelectionEvent e) {
		selectionChanged();
	}
    
    
    

	private void selectionChanged() {
		try {
			if(list.isSelectionEmpty()) {viewer.p(null);return;}
			Object key = list.getSelectedValue();
			labelTitle.setText(" "+key);
			viewer.p(data.get(key));
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
