package a.entity.gus.b.entitysrc2.gui.detail1.err;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import a.framework.Entity;
import a.framework.I;
import a.framework.P;
import a.framework.R;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20211004";}
	
	
	public static final String COL_ID = 				"id";
	public static final String COL_ENTITYNAME = 		"entityname";
	public static final String COL_FILENAME = 			"filename";
	public static final String COL_DATE = 				"date";
	public static final String COL_LINE = 				"line";
	public static final String COL_LINENB = 			"linenb";
	public static final String COL_LINEPOS = 			"linepos";
	public static final String COL_TYPE = 				"type";
	public static final String COL_DESCRIPTION =		"description";
	
	


	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private TableModel0 model;
	private JLabel numberLabel;

	private Object holder;
	private List errors;
	
	public EntityImpl() throws Exception
	{
		model = new TableModel0();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		
		scroll = new JScrollPane(table);
		
		numberLabel = new JLabel(" 0");
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(numberLabel,BorderLayout.SOUTH);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		holder = obj;
		errors = (List) ((R) holder).r("errors");
		
		numberLabel.setText(" "+errors.size());
		model.fireTableDataChanged();
	}
	
	private void reset()
	{
		holder = null;
		errors = null;
		
		numberLabel.setText(" 0");
		model.fireTableDataChanged();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private int getErrNumber()
	{return errors!=null ? errors.size() : 0;}
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 4;}
		public int getRowCount() {return getErrNumber();}
        
		public String getColumnName(int y)
		{
			if(y==0) return "file name";
			if(y==1) return "position";
			if(y==2) return "description";
			if(y==3) return "line";
			return null;
		}
        
		public Class getColumnClass(int y)
		{return String.class;}
        
		public boolean isCellEditable(int x, int y)
		{return false;}

		public Object getValueAt(int x, int y)
		{
			if(errors==null) return "";
			
			Map info = (Map) errors.get(x);
            
			if(y==0) return info.get(COL_FILENAME);
			if(y==1) return info.get(COL_LINENB)+":"+info.get(COL_LINEPOS);
			if(y==2) return info.get(COL_DESCRIPTION);
			if(y==3) return info.get(COL_LINE);
			return null;
		}
	}
}
