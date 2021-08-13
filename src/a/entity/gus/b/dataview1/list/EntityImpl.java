package a.entity.gus.b.dataview1.list;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

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
	public String creationDate() {return "20210813";}


	private Service viewer;
	private Service splitCust;
	private Service listRenderer;

	private JSplitPane split;
	private JList list;
	private JLabel label;
    
	private List data;


	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus.b.dataview1.object");
		splitCust = Outside.service(this,"gus.a.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this,"gus.b.dataview1.list.renderer");
	
		list = new JList();
		list.addListSelectionListener(this);
		listRenderer.p(list);
        
		label = new JLabel(" ");
        
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(label,BorderLayout.SOUTH);
        
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (List) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	
	private void updateGui() throws Exception
	{
		Vector vec = new Vector(data);
		list.setListData(vec);
		label.setText(" "+data.size());
		viewer.p(null);
	}
	
	private void resetGui() throws Exception
	{
		list.setListData(new Vector());
		label.setText(" ");
		viewer.p(null);
	}
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {viewer.p(null);return;}
			Object value = list.getSelectedValue();
			viewer.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}