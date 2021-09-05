package a.entity.gus.b.entitysrc2.gui.listing1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl extends S1 implements Entity, P, I, E, R, G, ListSelectionListener {
	public String creationDate() {return "20210830";}
	
	public static final String COL_FEATURES = "features";
	public static final String COL_CALLNB = "callnb";
	
	public static final int LOCK_MAX = 100;

	
	private Service fieldHolder;
	private Service linkerListField;
	private Service filterList;
	private Service engineHolder;

	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	private JLabel label;
	
	private Icon iconEntity;
	private Icon iconEntityLock;
	
	private Object engine;
	private List data;
	private Set lockSet;
	
	
	public EntityImpl() throws Exception {
		fieldHolder = Outside.service(this,"*gus.b.swing1.textfield.editor1");
		linkerListField = Outside.service(this,"gus.a.swing.table.textfield.linker");
		filterList = Outside.service(this,"gus.b.entitysrc2.gui.listing1.filter");
		engineHolder = Outside.service(this,"*gus.a.features.s.holder");
		
		iconEntity = (Icon) Outside.resource(this,"icon#ELEMENT_entity");
		iconEntityLock = (Icon) Outside.resource(this,"icon#ELEMENT_entity_lock");
		
		lockSet = new HashSet();

		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.isControlDown()){
					if(e.getKeyCode()==KeyEvent.VK_Q) lockAll();
					else if(e.getKeyCode()==KeyEvent.VK_W) unlockAll();
				}
			}
		});

		model = new TableModel0();
		table = new JTable(model);
		table.setGridColor(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setUI(null);
		table.setDefaultRenderer(String.class, new TableCellRenderer1());
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.isControlDown()){
					if(e.getKeyCode()==KeyEvent.VK_Q) lockSelected();
					else if(e.getKeyCode()==KeyEvent.VK_W) unlockSelected();
				}
			}
		});
		table.getSelectionModel().addListSelectionListener(this);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		initColumnSize(1,70);
		initColumnSize(2,20);
		
		linkerListField.p(new Object[]{table,field});
		fieldHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		
		engineHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{reload();}
		});
	}
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	public void p(Object obj) throws Exception
	{
		engine = obj;
		engineHolder.p(engine);
		reload();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{refresh();}
	
	
	public Object g() throws Exception
	{
		if(table.getSelectionModel().isSelectionEmpty()) return null;
		int row = table.getSelectedRow();
		return table.getValueAt(row, 0);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("field")) return field;
		if(key.equals("lockSet")) return lockSet;
		if(key.equals("keys")) return new String[] {"field", "lockSet"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void reload()
	{
		try {
			data = buildData();
			refresh();
		}
		catch(Exception e) {
			Outside.err(this, "reload()", e);
		}
	}
	
	
	private List buildData() throws Exception {
		Map map = (Map) ((G) engine).g();
		if(map==null) return null;
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		List list = new ArrayList();
		int nb = keys.size();
		for(int i=0;i<nb;i++) {
			String key = (String) keys.get(i);
			Map entityData = (Map) map.get(key);
			
			String features = (String) entityData.get(COL_FEATURES);
			int callNb = (int) entityData.get(COL_CALLNB);
			String call =  callNb>0 ? ""+callNb : "";
			
			list.add(new String[] {key, features, call});
		}
		return list;
	}
	
	
	private void refresh()
	{
		SwingUtilities.invokeLater(model);
		field.requestFocusInWindow();
	}
	
	
	
	private void lockSelected() {
		int[] rows = table.getSelectedRows();
		for(int row : rows) {
			String name = (String) table.getValueAt(row, 0);
			
			if(lockSet.size()<LOCK_MAX)
			lockSet.add(name);
		}
		SwingUtilities.invokeLater(model);
	}
	
	private void unlockSelected() {
		int[] rows = table.getSelectedRows();
		for(int row : rows) {
			String name = (String) table.getValueAt(row, 0);
			lockSet.remove(name);
		}
		SwingUtilities.invokeLater(model);
	}
	
	
	
	
	private void lockAll() {
		for(int i=0;i<table.getRowCount();i++) {
			String name = (String) table.getValueAt(i, 0);
			
			if(lockSet.size()<LOCK_MAX)
			lockSet.add(name);
		}
		SwingUtilities.invokeLater(model);
	}
	
	private void unlockAll() {
		for(int i=0;i<table.getRowCount();i++) {
			String name = (String) table.getValueAt(i, 0);
			lockSet.remove(name);
		}
		SwingUtilities.invokeLater(model);
	}
	
	
	
	
	
	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private List list = new ArrayList();
		
		public int getColumnCount() {
			return 3;
		}
		
		public int getRowCount() {
			return list.size();
		}
		
		public String getColumnName(int y) {
			if(y==0) return "Entity name";
			if(y==1) return "Features";
			if(y==2) return "N";
			
			return "";
		}
		
		public Class getColumnClass(int y) {
			return String.class;
		}

		public Object getValueAt(int x, int y){
			String[] infos = (String[]) list.get(x);
			return infos[y];
		}
		
		public void run() {
			list = buildListForTable();
			label.setText(" number: "+list.size());
			fireTableDataChanged();
			field.requestFocusInWindow();
		}
	}
	
	
	private List buildListForTable()
	{
		try
		{
			if(data==null) return new ArrayList();
			String search = (String) fieldHolder.g();
			return (List) filterList.t(new Object[] {data, search, lockSet});
		}
		catch(Exception e)
		{Outside.err(this,"buildListForTable()",e);}
		return null;
	}
	
	
	public static final Color COLOR_SELECT = new Color(244,244,244);
	public static final Color COLOR_UNSELECT = Color.WHITE;
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1() {
			super();
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			String s = (String) value;
			
			if(column==0) {
				Icon icon = lockSet.contains(s) ? iconEntityLock : iconEntity;
				setIcon(icon);
				setText(s);
			}
			else {
				setIcon(null);
				setText(" "+s);
			}
			
			setBackground(getBackground(isSelected));
			return this;
		}
		
		public Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
	}

	

	public void valueChanged(ListSelectionEvent e) {
		selectionChanged();
	}
	
	private void selectionChanged() {
		send(this,"selectionChanged()");
	}
}
