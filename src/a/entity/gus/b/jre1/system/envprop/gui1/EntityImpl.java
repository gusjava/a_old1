package a.entity.gus.b.jre1.system.envprop.gui1;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20210825";}

	private Service renderer;
	
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		renderer = Outside.service(this,"gus.a.swing.table.cust.renderer.mapstring1");
		
		Map m1 = System.getProperties();
		Map m2 = System.getenv();
    	
		JScrollPane p1 = new JScrollPane(new JTableMap(m1));
		setBorder(p1,"System properties  ("+m1.size()+" values)");
    	
		JScrollPane p2 = new JScrollPane(new JTableMap(m2));
		setBorder(p2,"System env  ("+m2.size()+" values)");
    	
		panel = new JPanel(new GridLayout(2,1));
		panel.add(p1);
		panel.add(p2);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private void setBorder(JComponent p, String title)
	{
 		TitledBorder border = BorderFactory.createTitledBorder(title);
		p.setBorder(border);	
	}
	
	
	private String[][] content(Map map)
	{
		String[][] content = new String[map.size()][2];
		ArrayList keys = new ArrayList(map.keySet());
 		Collections.sort(keys);
 		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			String value = (String) map.get(key);
			content[i][0]=key;
			content[i][1]=value;
		}
		return content;
	}

	
	
	private class JTableMap extends JTable
	{
		public JTableMap(Map map) throws Exception
		{
			super(new TableModel1(content(map)));
			getTableHeader().setReorderingAllowed(false);
			setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			setCellSelectionEnabled(true);
			renderer.p(this);
		}
	}
	
	
	private class TableModel1 extends AbstractTableModel
	{
		private String[][] content;
		public TableModel1(String[][] content)
		{this.content = content;}
		
		public int getRowCount() {return content.length;}
		public int getColumnCount() {return 2;}

		public String getColumnName(int column)
		{return column==0 ? "key" : "value";}

		public Class getColumnClass(int column)
		{return String.class;}

		public boolean isCellEditable(int rowIndex, int columnIndex)
		{return false;}

		public Object getValueAt(int index, int column) 
		{return content[index][column];}
	}
}
