package a.entity.gus.b.entitysrc1.listinggui1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl extends S1 implements Entity, G, P, I, E, ActionListener, ListSelectionListener {
	public String creationDate() {return "20210818";}

	public static final String ICONID = "entity";

	private Service fieldHolder;
	private Service buildJList;
	private Service linkerListField;
	private Service filterList;

	private JPanel panel;
	private JComponent field;
	private JList list;
	private JLabel label;
	
	private G listingProvider;

	
	public EntityImpl() throws Exception {
		fieldHolder = Outside.service(this,"*gus.b.swing1.textfield.editor1");
		buildJList = Outside.service(this,"gus.b.swing1.list.build.fromicon");
		linkerListField = Outside.service(this,"gus.a.swing.list.textfield.linker");
		filterList = Outside.service(this,"gus.b.entitysrc1.listinggui1.filter");
		
		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
		list = (JList) buildJList.t(ICONID);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		linkerListField.p(new Object[]{list,field});

		list.addListSelectionListener(this);
		fieldHolder.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return list.getSelectedValue();}
	
	
	public void p(Object obj) throws Exception
	{
		listingProvider = (G) obj;
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			if(listingProvider==null) throw new Exception("listingProvider not initialized yet");
			String search = (String) fieldHolder.g();
			
			List listing = (List) listingProvider.g();
			Vector keys = toVector((List) filterList.t(new Object[] {listing, search}));
			list.setListData(keys);
			label.setText(" number: "+keys.size());
			
			field.requestFocusInWindow();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	private Vector toVector(List keys)
	{
		if(keys==null) return new Vector();
		return new Vector(keys);
	}



	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}
