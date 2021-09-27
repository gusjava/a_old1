package a.entity.gus.b.entitysrc2.gui.detail1.infos.uplinks;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210920";}


	private Service findLinks;
	private Service renderer;
	private Service custUI;
	
	private JPanel panel;
	
	private TreeModel1 model;
	private JTree tree;

	private Object holder;
	private String entityName;
	private Connection cx;
	

	public EntityImpl() throws Exception
	{
		findLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.find1.sorted");
		renderer = Outside.service(this,"gus.b.swing1.tree.cust.renderer.entityname");
		custUI = Outside.service(this,"gus.b.swing1.tree.cust.ui.expandcollapseicons2");

		model = new TreeModel1();
		tree = new JTree(model);
		renderer.p(tree);
		custUI.p(tree);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree), BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		holder = obj;
		
		entityName = (String) ((R) holder).r("entityName");
		cx = (Connection) ((R) holder).r("cx");
		
		model = new TreeModel1();
		tree.setModel(model);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private void reset() throws Exception
	{
		holder = null;
		entityName = null;
		cx = null;
		
		model = new TreeModel1();
		tree.setModel(model);
	}
	
	
	
	private List links(String name)
	{
		try{return (List) findLinks.t(new Object[] {cx, name});}
		catch(Exception e)
		{Outside.err(this,"links(String)",e);}
		return new ArrayList();
	}
	
	
	
	
	private class TreeModel1 implements TreeModel
	{
		public Object getRoot()
		{return entityName;}

		public Object getChild(Object parent, int index)
		{return links((String) parent).get(index);}

		public int getChildCount(Object parent)
		{return links((String) parent).size();}

		public boolean isLeaf(Object node)
		{return links((String) node).isEmpty();}

		public int getIndexOfChild(Object parent, Object child)
		{return links((String) parent).indexOf(child);}
		
		public void valueForPathChanged(TreePath path, Object newValue) {}
		public void addTreeModelListener(TreeModelListener l) {}
		public void removeTreeModelListener(TreeModelListener l) {}
	}
}
