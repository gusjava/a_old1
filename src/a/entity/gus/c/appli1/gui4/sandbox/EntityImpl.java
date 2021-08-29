package a.entity.gus.c.appli1.gui4.sandbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import a.framework.E;
import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.S;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20210827";}
	
	public static final String DISPLAY_ACTION_UNIQUE = "ELEMENT_entity_unique#Load unique";

	
	private Service watcher;
	private Service barFactory;
	private Service actionBuilder;
	private Service uniqueLoader;
	private Service viewer;
	
	private List listEntityClass;
	private Map mapEntityLoaded;
	private Map mapEntityUnique;
	
	private Icon iconEntityClass;
	private Icon iconClasspathAppli;
	private Icon iconClasspathEntity;
	private Icon iconEntityUnique;
	
	private JPanel panel;
	private JSplitPane split;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JLabel labelTitle;

	
	public EntityImpl() throws Exception {
		watcher = Outside.service(this,"gus.b.gyem1.watcher");
		barFactory = Outside.service(this,"gus.a.swing.toolbar.factory1");
		actionBuilder = Outside.service(this,"gus.b.actions1.builder0");
		uniqueLoader = Outside.service(this,"m016.t.entity.unique");
		viewer = Outside.service(this,"*gus.b.dataview1.object");
		
		listEntityClass = (List) Outside.resource(this,"g#gus.b.entityclass2.listing.main");
		mapEntityLoaded = (Map) Outside.resource(this, "g#m018.t.entity.findclass");
		mapEntityUnique = (Map) Outside.resource(this, "g#m016.t.entity.unique");
		
		iconEntityClass = (Icon) Outside.resource(this,"icon#ELEMENT_entity_classfile");
		iconClasspathAppli = (Icon) Outside.resource(this,"icon#GUI_classpath_appli");
		iconClasspathEntity = (Icon) Outside.resource(this,"icon#GUI_classpath_entity");
		iconEntityUnique = (Icon) Outside.resource(this,"icon#ELEMENT_entity_unique");
		

		model = new TableModel0();
		
		table = new JTable(model);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(String.class, new TableCellRenderer1());
		
		initColumnSize(1,25);
		initColumnSize(2,25);

		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		Action actionUnique = (Action) actionBuilder.t(new Object[] {DISPLAY_ACTION_UNIQUE, (E) this::loadUnique});
		
		JToolBar bar = (JToolBar) barFactory.i();
		bar.add(actionUnique);

		labelTitle = new JLabel(" ");
		
		JPanel panelLeft = new JPanel(new BorderLayout());
		panelLeft.add(scroll, BorderLayout.CENTER);
		panelLeft.add(bar, BorderLayout.SOUTH);
		
		JPanel panelRight = new JPanel(new BorderLayout());
		panelRight.add(labelTitle, BorderLayout.NORTH);
		panelRight.add((JComponent) viewer.i(), BorderLayout.CENTER);
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);
		
		split.setLeftComponent(panelLeft);
		split.setRightComponent(panelRight);
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);

		S watcherEntityClass = (S) watcher.r("supportEntityLoaded");
		S watcherEntityUnique = (S) watcher.r("supportEntityUnique");

		watcherEntityClass.addActionListener(this);
		watcherEntityUnique.addActionListener(this);
	}
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}


	public void actionPerformed(ActionEvent e) {
		refresh();
	}
	
	
	private void refresh() {
		SwingUtilities.invokeLater(model);
	}
	
	
	
	private void loadUnique() {
		try {
			int index = table.getSelectedRow();
			if(index==-1) return;
			
			String entityName = (String) listEntityClass.get(index);
			Object entity = uniqueLoader.t(entityName);
			viewer.p(entity);
			refresh();
		}
		catch(Exception e) {
			Outside.err(this,"loadUnique()",e);
		}
	}
	
	
	private String findLoaded(String entityName) {
		if(!mapEntityLoaded.containsKey(entityName)) return "";
		Class c = (Class) mapEntityLoaded.get(entityName);
		ClassLoader cl = c.getClassLoader();
		return cl instanceof URLClassLoader ? "entity" : "appli";
	}
	
	private String findUnique(String entityName) {
		return mapEntityUnique.containsKey(entityName) ? "unique" : "";
	}
	
	
	
	
	
	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		public int getColumnCount() {
			return 3;
		}
		
		public int getRowCount() {
			return listEntityClass.size();
		}
		
		public String getColumnName(int y) {
			if(y==0) return "Entity name";
			if(y==1) return "cp";
			if(y==2) return "+";
			
			return "";
		}
		
		public Class getColumnClass(int y) {
			return String.class;
		}

		public Object getValueAt(int x, int y){
			String entityName = (String) listEntityClass.get(x);
			if(y==0) return entityName;
			if(y==1) return findLoaded(entityName);
			return findUnique(entityName);
		}
		
		public void run() {
			int index = table.getSelectedRow();
			fireTableDataChanged();
			if(index!=-1) table.getSelectionModel().setSelectionInterval(index, index);
		}
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
				setIcon(iconEntityClass);
				setText(s);
			}
			else if(column==1) {
				if(s.equals("appli")) setIcon(iconClasspathAppli);
				else if(s.equals("entity")) setIcon(iconClasspathEntity);
				else setIcon(null);
				
				setText("");
			}
			else if(column==2) {
				if(s.equals("unique")) setIcon(iconEntityUnique);
				else setIcon(null);
				
				setText("");
			}
			
			setBackground(getBackground(isSelected));
			return this;
		}
		
		public Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
	}
}
