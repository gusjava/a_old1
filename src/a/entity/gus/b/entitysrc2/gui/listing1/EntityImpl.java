package a.entity.gus.b.entitysrc2.gui.listing1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import a.framework.E;
import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I, E, ActionListener {
	public String creationDate() {return "20210830";}
	
	public static final String KEY_FEATURES = "features";

	
	private Service fieldHolder;
	private Service linkerListField;
	private Service filterList;

	private JPanel panel;
	private JScrollPane scroll;
	private JTable table;
	private TableModel0 model;
	private JComponent field;
	private JLabel label;
	
	private Icon iconEntity;
	
	private List data;
	
	
	public EntityImpl() throws Exception {
		fieldHolder = Outside.service(this,"*gus.b.swing1.textfield.editor1");
		linkerListField = Outside.service(this,"gus.a.swing.table.textfield.linker");
		filterList = Outside.service(this,"gus.b.entitysrc2.gui.listing1.filter");
		iconEntity = (Icon) Outside.resource(this,"icon#ELEMENT_entity");

		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();

		model = new TableModel0();
		table = new JTable(model);
		table.setGridColor(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setUI(null);
		table.setDefaultRenderer(String.class, new TableCellRenderer1());
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		initColumnSize(1,70);
		
		linkerListField.p(new Object[]{table,field});
		fieldHolder.addActionListener(this);
	}
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		if(m!=null) {
			List keys = new ArrayList(m.keySet());
			Collections.sort(keys);
			
			data = new ArrayList();
			int nb = keys.size();
			for(int i=0;i<nb;i++) {
				String key = (String) keys.get(i);
				String features = (String) ((Map) m.get(key)).get(KEY_FEATURES);
				data.add(new String[] {key, features});
			}
		}
		else {
			data = null;
		}
		
		refresh();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{refresh();}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void refresh()
	{
		SwingUtilities.invokeLater(model);
		field.requestFocusInWindow();
	}
	
	
	
	
	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private List list = new ArrayList();
		
		public int getColumnCount() {
			return 2;
		}
		
		public int getRowCount() {
			return list.size();
		}
		
		public String getColumnName(int y) {
			if(y==0) return "Entity name";
			if(y==1) return "Features";
			
			return "";
		}
		
		public Class getColumnClass(int y) {
			return String.class;
		}

		public Object getValueAt(int x, int y){
			String[] name = (String[]) list.get(x);
			return name[y];
		}
		
		public void run() {
			list = buildList();
			label.setText(" number: "+list.size());
			fireTableDataChanged();
			field.requestFocusInWindow();
		}
	}
	
	
	private List buildList()
	{
		try
		{
			if(data==null) return new ArrayList();
			String search = (String) fieldHolder.g();
			return (List) filterList.t(new Object[] {data, search});
		}
		catch(Exception e)
		{Outside.err(this,"buildList()",e);}
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
				setIcon(iconEntity);
				setText(s);
			}
			else if(column==1) {
				setIcon(null);
				setText(" "+s);
			}
			
			setBackground(getBackground(isSelected));
			return this;
		}
		
		public Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
	}
}
