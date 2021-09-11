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

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import a.framework.E;
import a.framework.F;
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
	
	public static final String DISPLAY_CREATE = "ELEMENT_entity_add#Create entity [F1]";
	public static final String DISPLAY_DELETE = "ELEMENT_entity_delete#Delete entity [DEL]";
	public static final String DISPLAY_RENAME = "ELEMENT_entity_rename#Rename entity [F2]";
	public static final String DISPLAY_DUPLICATE = "ELEMENT_entity_duplicate#Duplicate entity [F3]";

	
	private Service fieldHolder;
	private Service engineHolder;
	
	private Service linkerListField;
	private Service filterList;
	private Service clipboard;
	private Service actionBuilder;
	private Service toolbarFactory;
	private Service clearCopyPasteCut;

	private Service entityDelete;
	private Service entityCreate;
	private Service entityRename;
	private Service entityDuplicate;

	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	private JLabel labelNumber;
	private JToolBar bar;
	
	private Icon iconEntity;
	private Icon iconEntityLock;
	
	private Action actionCreate;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;
	
	private Object engine;
	private List data;
	private Set lockSet;
	
	
	public EntityImpl() throws Exception {
		fieldHolder = Outside.service(this,"*gus.b.swing1.textfield.editor1");
		engineHolder = Outside.service(this,"*gus.a.features.s.holder");
		
		linkerListField = Outside.service(this,"gus.a.swing.table.textfield.linker");
		filterList = Outside.service(this,"gus.b.entitysrc2.gui.listing1.filter");
		clipboard = Outside.service(this,"gus.a.clipboard.string");
		actionBuilder = Outside.service(this,"gus.b.actions1.builder0");
		toolbarFactory = Outside.service(this,"gus.a.swing.toolbar.factory1");
		clearCopyPasteCut = Outside.service(this,"gus.a.swing.comp.action.clear.copypastecut");
		
		entityDelete = Outside.service(this,"gus.b.entitysrc2.perform.delete.ask");
		entityCreate = Outside.service(this,"gus.b.entitysrc2.perform.create.ask");
		entityRename = Outside.service(this,"gus.b.entitysrc2.perform.rename.ask");
		entityDuplicate = Outside.service(this,"gus.b.entitysrc2.perform.duplicate.ask");
		
		iconEntity = (Icon) Outside.resource(this,"icon#ELEMENT_entity");
		iconEntityLock = (Icon) Outside.resource(this,"icon#ELEMENT_entity_lock");
		
		actionCreate = (Action) actionBuilder.t(new Object[] {DISPLAY_CREATE, (E) this::entityCreate});
		actionDelete = (Action) actionBuilder.t(new Object[] {DISPLAY_DELETE, (E) this::entityDelete});
		actionRename = (Action) actionBuilder.t(new Object[] {DISPLAY_RENAME, (E) this::entityRename});
		actionDuplicate = (Action) actionBuilder.t(new Object[] {DISPLAY_DUPLICATE, (E) this::entityDuplicate});
		
		
		lockSet = new HashSet();

		labelNumber = new JLabel(" ");
		bar = (JToolBar) toolbarFactory.i();
		
		bar.add(actionCreate);
		bar.add(actionDelete);
		bar.add(actionRename);
		bar.add(actionDuplicate);
		
		field = (JComponent) fieldHolder.i();
		field.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(e.isControlDown()){
					if(code==KeyEvent.VK_Q) lockAll();
					else if(code==KeyEvent.VK_W) unlockAll();
				}
				else {
					if(code==KeyEvent.VK_F1) entityCreate();
					else if(code==KeyEvent.VK_F2) entityRename();
					else if(code==KeyEvent.VK_F3) entityDuplicate();
					else if(code==KeyEvent.VK_F5) forceReload();
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
		table.getSelectionModel().addListSelectionListener(this);
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(e.isControlDown()){
					if(code==KeyEvent.VK_Q) lockSelected();
					else if(code==KeyEvent.VK_W) unlockSelected();
					else if(code==KeyEvent.VK_C) copySelection();
					else if(code==KeyEvent.VK_V) pasteSelection();
				}
				else {
					if(code==KeyEvent.VK_DELETE) entityDelete();
					else if(code==KeyEvent.VK_F1) entityCreate();
					else if(code==KeyEvent.VK_F2) entityRename();
					else if(code==KeyEvent.VK_F3) entityDuplicate();
					else if(code==KeyEvent.VK_F5) forceReload();
				}
			}
		});
		
		clearCopyPasteCut.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(labelNumber,BorderLayout.CENTER);
		bottomPanel.add(bar,BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		
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
		
		refreshActions();
	}
	
	/*
	 * FEATURES
	 */
	
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
	{return getSelectedName();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("field")) return field;
		if(key.equals("table")) return table;
		if(key.equals("lockSet")) return lockSet;
		if(key.equals("keys")) return new String[] {"field", "table", "lockSet"};
		
		throw new Exception("Unknown key: "+key);
	}
	

	
	
	
	/*
	 * INTERNAL
	 */
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	private void forceReload()
	{
		try {
			((E) engine).e();
			data = buildFullData();
			refresh();
		}
		catch(Exception e) {
			Outside.err(this, "forceReload()", e);
		}
	}
	
	
	private void reload()
	{
		try {
			data = buildFullData();
			refresh();
		}
		catch(Exception e) {
			Outside.err(this, "reload()", e);
		}
	}
	
	
	private List buildFullData() throws Exception {
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
	
	private List buildListForTable()
	{
		try
		{
			if(data==null) return new ArrayList();
			
			String search = (String) fieldHolder.g();
			String devId = (String) ((R) engine).r("devId");
			return (List) filterList.t(new Object[] {data, search, devId, lockSet});
		}
		catch(Exception e)
		{Outside.err(this,"buildListForTable()",e);}
		return null;
	}
	
	
	private String getSelectedName()
	{
		if(table.getSelectionModel().isSelectionEmpty()) return null;
		int row = table.getSelectedRow();
		return (String) table.getValueAt(row, 0);
	}
	
	
	private boolean hasSelection()
	{return !table.getSelectionModel().isSelectionEmpty();}
	
	
	private boolean canDeleteSelected() throws Exception
	{return hasSelection() && permission("canDeleteEntity",getSelectedName());}
	
	
	private boolean canRenameSelected() throws Exception
	{return hasSelection() && permission("canRenameEntity",getSelectedName());}
	
	
	private boolean canDuplicateSelected() throws Exception
	{return hasSelection() && permission("canDuplicateEntity",getSelectedName());}
	
	
	private boolean permission(String permission, String entityName) throws Exception
	{return engine!=null && ((F) engine).f(new Object[] {permission, entityName});}
	
	
	
	
	
	private void _refreshActions()
	{
		try {refreshActions();}
		catch(Exception e)
		{Outside.err(this,"_refreshActions()",e);}
	}
	
	private void refreshActions() throws Exception
	{
		actionDelete.setEnabled(canDeleteSelected());
		actionRename.setEnabled(canRenameSelected());
		actionDuplicate.setEnabled(canDuplicateSelected());
	}
	
	
	/*
	 * TABLE MODEL
	 */
	
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
			labelNumber.setText(" "+list.size());
			fireTableDataChanged();
			field.requestFocusInWindow();
		}
	}
	
	
	
	
	/*
	 * TABLE RENDERER
	 */
	
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
		_refreshActions();
		selectionChanged();
	}
	
	private void selectionChanged() {
		send(this,"selectionChanged()");
	}
	
	
	
	
	/*
	 * ACTIONS LOCALES
	 */
	
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
	
	private void copySelection() {
		try {
			StringBuffer b = new StringBuffer();
			int[] rows = table.getSelectedRows();
			for(int i=0;i<rows.length;i++) {
				String name = (String) table.getValueAt(rows[i], 0);
				b.append(name);
				if(i<rows.length-1) b.append("\n");
			}
			clipboard.p(b.toString());
		}
		catch(Exception e) {
			Outside.err(this,"copySelection()",e);
		}
	}
	
	private void pasteSelection() {
		try {
			String s = (String) clipboard.g();
			String[] n = s.split("[\n\r]+");
			for(int i=0;i<n.length;i++) {
				if(lockSet.size()<LOCK_MAX)
				lockSet.add(n[i]);
			}
			SwingUtilities.invokeLater(model);
		}
		catch(Exception e) {
			Outside.err(this,"pasteSelection()",e);
		}
	}
	
	
	/*
	 * ACTIONS GLOBALES
	 */
	
	private void entityCreate() {
		try {
			entityCreate.p(new Object[] {engine, table});
		}
		catch(Exception e) {
			Outside.err(this,"entityCreate()",e);
		}
	}
	
	private void entityDelete() {
		try {
			if(!canDeleteSelected()) return;
			entityDelete.p(new Object[] {engine, getSelectedName(), table});
		}
		catch(Exception e) {
			Outside.err(this,"entityDelete()",e);
		}
	}

	private void entityRename() {
		try {
			if(!canRenameSelected()) return;
			entityRename.p(new Object[] {engine, getSelectedName(), table});
		}
		catch(Exception e) {
			Outside.err(this,"entityRename()",e);
		}
	}

	private void entityDuplicate() {
		try {
			if(!canDuplicateSelected()) return;
			entityDuplicate.p(new Object[] {engine, getSelectedName(), table});
		}
		catch(Exception e) {
			Outside.err(this,"entityDuplicate()",e);
		}
	}
}
