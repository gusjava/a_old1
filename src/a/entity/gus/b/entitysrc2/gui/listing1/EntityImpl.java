package a.entity.gus.b.entitysrc2.gui.listing1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;
import a.framework.V;

public class EntityImpl extends S1 implements Entity, P, I, E, R, G, V, ListSelectionListener {
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
	private JLabel labelNumberLocked;
	private JLabel labelNumberError;
	private JToolBar bar;
	
	private Icon iconEntity;
	private Icon iconEntityLock;
	private Icon iconLock;
	private Icon iconErr;
	
	private Action actionCreate;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;
	
	private Object engine;
	
	private Map map;
	private List names;
	private List dataFull;
	private List dataFiltered;
	
	private Set lockSet;
	private Map errorMap;
	private String selectedPath;
	private String modifiedPath;
	
	
	
	public EntityImpl() throws Exception
	{
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
		iconLock = (Icon) Outside.resource(this,"icon#UTIL_lockR");
		iconErr = (Icon) Outside.resource(this,"icon#UTIL_errorR");
		
		actionCreate = (Action) actionBuilder.t(new Object[] {DISPLAY_CREATE, (E) this::entityCreate});
		actionDelete = (Action) actionBuilder.t(new Object[] {DISPLAY_DELETE, (E) this::entityDelete});
		actionRename = (Action) actionBuilder.t(new Object[] {DISPLAY_RENAME, (E) this::entityRename});
		actionDuplicate = (Action) actionBuilder.t(new Object[] {DISPLAY_DUPLICATE, (E) this::entityDuplicate});
		
		
		lockSet = new HashSet();
		errorMap = new HashMap();

		labelNumber = new JLabel(" ");
		labelNumberLocked = new JLabel(" ");
		labelNumberError = new JLabel(" ");
		
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
		
		JPanel bottomPanel = wce(labelNumber, wc(labelNumberLocked, labelNumberError), bar);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		
		initColumnSize(1,70);
		initColumnSize(2,20);
		
		linkerListField.p(new Object[]{table,field});
		fieldHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(()->{
					updateTable();
					refreshLocked();
				});
			}
		});
		
		engineHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("changed()")) reload();
				else if(s.equals("selected()")) select();
				else if(s.equals("changedAndSelected()")) reloadAndSelect();
			}
		});
		
		refreshActions_();
	}
	
	
	
	private JPanel wc(JComponent w, JComponent c)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(w,BorderLayout.WEST);
		panel.add(c,BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel wce(JComponent w, JComponent c, JComponent e)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(w,BorderLayout.WEST);
		panel.add(c,BorderLayout.CENTER);
		panel.add(e,BorderLayout.EAST);
		return panel;
	}
	
	
	/*
	 * FEATURES
	 */
	
	public void p(Object obj) throws Exception
	{
		engine = obj;
		engineHolder.p(engine);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{reload();}
	
	
	public Object g() throws Exception
	{
		String selection = getSelectedName();
		if(selection==null) return null;
		
		if(selectedPath!=null && selectedPath.startsWith(selection+"@")) selection = selectedPath;
		selectedPath = null;
		return selection;
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("field")) return field;
		if(key.equals("fieldHolder")) return fieldHolder;
		if(key.equals("table")) return table;
		if(key.equals("lockSet")) return lockSet;
		
		if(key.equals("keys")) return new String[] {"field", "fieldHolder", "table", "lockSet"};
		
		throw new Exception("Unknown key: "+key);
	}
	

	
	public void v(String key, Object obj) throws Exception
	{
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	
	
	
	private void reload()
	{
		try
		{
			buildDataFull();
			
			SwingUtilities.invokeLater(()->{
				updateTable();
				refreshLocked();
			});
		}
		catch(Exception e)
		{Outside.err(this, "reload()", e);}
	}
	
	
	private void select()
	{
		try
		{
			selectedPath = (String) ((R) engine).r("selected");
			
			final String name = selectedPath.split("@",2)[0];
			addToLocked(name);
			
			SwingUtilities.invokeLater(()->{
				updateTable();
				selectEntity(name);
			});
		}
		catch(Exception e)
		{Outside.err(this, "select()", e);}
	}
	
	
	private void reloadAndSelect()
	{
		try
		{
			selectedPath = (String) ((R) engine).r("selected");
			modifiedPath = (String) ((R) engine).r("modified");
			
			final String selectedName = selectedPath.split("@",2)[0];
			final String modifiedName = modifiedPath.split("@",2)[0];
			
			buildDataFull();
			addToLocked(modifiedName);
			
			SwingUtilities.invokeLater(()->{
				updateTable();
				refreshLocked();
				selectEntity(selectedName);
			});
		}
		catch(Exception e)
		{Outside.err(this, "reloadAndSelect()", e);}
	}
	
	
	private void forceReload()
	{
		try {
			((E) engine).e();
			buildDataFull();
			
			SwingUtilities.invokeLater(()->{
				updateTable();
				refreshLocked();
			});
		}
		catch(Exception e) {
			Outside.err(this, "forceReload()", e);
		}
	}
	
	
	
	
	
	
	

	
	
	
	private void refreshLocked()
	{
		if(names!=null) lockSet.retainAll(names);
		if(lockSet.isEmpty())
		{
			labelNumberLocked.setIcon(null);
			labelNumberLocked.setText(" ");
		}
		else
		{
			labelNumberLocked.setIcon(iconLock);
			labelNumberLocked.setText(""+lockSet.size());
		}
	}
	
	
	private void refreshErrorNumber()
	{
		if(errorMap.isEmpty())
		{
			labelNumberError.setIcon(null);
			labelNumberError.setText(" ");
		}
		else
		{
			labelNumberError.setIcon(iconErr);
			labelNumberError.setText(""+errorMap.size());
		}
	}
	
	
	
	/*
	 * BUILD DATA FULL
	 */
	
	private void buildDataFull() throws Exception
	{
		map = (Map) ((G) engine).g();
		errorMap = (Map) ((R) engine).r("errors");
		refreshErrorNumber();
		
		names = new ArrayList(map.keySet());
		Collections.sort(names);
		
		dataFull = new ArrayList();
		int nb = names.size();
		for(int i=0;i<nb;i++) {
			String key = (String) names.get(i);
			Map entityData = (Map) map.get(key);
			
			String features = (String) entityData.get(COL_FEATURES);
			int callNb = (int) entityData.get(COL_CALLNB);
			String call =  callNb>0 ? ""+callNb : "";
			
			dataFull.add(new String[] {key, features, call});
		}
	}
	
	
	
	/*
	 * BUILD DATA FILTERED
	 */
	
	
	private void buildDataFiltered() throws Exception
	{
		if(dataFull==null) dataFiltered = null;
		
		String search = (String) fieldHolder.g();
		String devId = (String) ((R) engine).r("devId");
		dataFiltered = (List) filterList.t(new Object[] {dataFull, search, devId, lockSet, errorMap.keySet()});
	}
	
	
	
	public void updateTable()
	{
		try
		{
			buildDataFiltered();
			labelNumber.setText(" "+getFilteredNumber()+"  ");
			model.fireTableDataChanged();
		}
		catch(Exception e)
		{Outside.err(this,"updateTable()",e);}
	}
	

	
	private int getFilteredNumber()
	{return dataFiltered!=null ? dataFiltered.size() : 0;}
	
	
	
	private int getErrorNumber(String entityName)
	{
		if(errorMap==null || !errorMap.containsKey(entityName)) return 0;
		return ((List) errorMap.get(entityName)).size();
	}
	
	
	
	
	
	private void selectEntity(String name)
	{
		for(int i=0;i<model.getRowCount();i++)
		if(model.getValueAt(i,0).equals(name)) 
		{
			table.getSelectionModel().setSelectionInterval(i, i);
	        ensureRowIsVisible(i);
			return;
		}
	}
	
	
	private void ensureRowIsVisible(int row)
	{
		Rectangle rect = table.getCellRect(row,0,true);
		table.scrollRectToVisible(rect);
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
	
	
	
	
	
	private void refreshActions()
	{
		try {refreshActions_();}
		catch(Exception e)
		{Outside.err(this,"refreshActions()",e);}
	}
	
	private void refreshActions_() throws Exception
	{
		actionDelete.setEnabled(canDeleteSelected());
		actionRename.setEnabled(canRenameSelected());
		actionDuplicate.setEnabled(canDuplicateSelected());
	}
	
	
	/*
	 * TABLE MODEL
	 */
	
	private class TableModel0 extends AbstractTableModel
	{
		
		public int getColumnCount()
		{return 3;}
		
		public int getRowCount()
		{return getFilteredNumber();}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Entity name";
			if(y==1) return "Features";
			if(y==2) return "N";
			return "";
		}
		
		public Class getColumnClass(int y)
		{
			return String.class;
		}

		public Object getValueAt(int x, int y)
		{
			if(dataFiltered==null) return null;
			String[] infos = (String[]) dataFiltered.get(x);
			return infos[y];
		}
	}
	
	
	
	
	/*
	 * TABLE RENDERER
	 */
	
	public static final Color BG_SELECTED = new Color(244,244,244);
	public static final Color BG_UNSELECTED = Color.WHITE;
	
	public static final Color FG_COMPILED = Color.BLACK;
	public static final Color FG_UNCOMPILED = Color.RED;
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			String s = (String) value;
			
			String entityName = (String) table.getValueAt(row,0);
			int errNumber = getErrorNumber(entityName);
			
			if(column==0) 
			{
				Icon icon = lockSet.contains(s) ? iconEntityLock : iconEntity;
				setIcon(icon);
				
				if(errNumber==0) setText(s);
				else setText(s+" ("+errNumber+")");
			}
			else
			{
				setIcon(null);
				setText(" "+s);
			}
			
			setBackground(getBackground(isSelected));
			setForeground(getForeground(entityName));
			return this;
		}
		
		public Color getBackground(boolean isSelected)
		{return isSelected ? BG_SELECTED : BG_UNSELECTED;}
		
		public Color getForeground(String s)
		{return errorMap!=null && errorMap.containsKey(s) ? FG_UNCOMPILED : FG_COMPILED;}
	}

	

	public void valueChanged(ListSelectionEvent e)
	{
		refreshActions();
		selectionChanged();
	}
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private boolean addToLocked(String name)
	{
		if(lockSet.contains(name)) return true;
		if(lockSet.size()>=LOCK_MAX) return false;
		
		lockSet.add(name);
		refreshLocked();
		return true;
	}
	
	
	private boolean removeFromLocked(String name)
	{
		if(!lockSet.contains(name)) return true;
		
		lockSet.remove(name);
		refreshLocked();
		return true;
	}
	
	
	
	/*
	 * ACTIONS LOCALES
	 */
	
	private void lockSelected() {
		int[] rows = table.getSelectedRows();
		for(int row : rows) {
			String name = (String) table.getValueAt(row, 0);
			addToLocked(name);
		}
		SwingUtilities.invokeLater(()->{
			String name = getSelectedName();
			updateTable();
			selectEntity(name);
		});
	}
	
	private void unlockSelected() {
		int[] rows = table.getSelectedRows();
		for(int row : rows) {
			String name = (String) table.getValueAt(row, 0);
			removeFromLocked(name);
		}
		SwingUtilities.invokeLater(()->{
			String name = getSelectedName();
			updateTable();
			selectEntity(name);
		});
	}
	
	private void lockAll() {
		for(int i=0;i<table.getRowCount();i++) {
			String name = (String) table.getValueAt(i, 0);
			addToLocked(name);
		}
		SwingUtilities.invokeLater(()->{
			String name = getSelectedName();
			updateTable();
			selectEntity(name);
		});
	}
	
	private void unlockAll() {
		for(int i=0;i<table.getRowCount();i++) {
			String name = (String) table.getValueAt(i, 0);
			removeFromLocked(name);
		}
		SwingUtilities.invokeLater(()->{
			String name = getSelectedName();
			updateTable();
			selectEntity(name);
		});
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
			for(int i=0;i<n.length;i++) addToLocked(n[i]);
			
			SwingUtilities.invokeLater(()->{
				updateTable();
//				field.requestFocusInWindow();
			});
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
