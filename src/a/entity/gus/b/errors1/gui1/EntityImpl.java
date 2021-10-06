package a.entity.gus.b.errors1.gui1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20210826";}

	
	private Service watcher;
	private Service exceptionView;
	
	private List errList;

	private JSplitPane split;
	private JTable table;
	private TableModel0 model;
	private JLabel numberLabel;
	

	public EntityImpl() throws Exception
	{
		watcher = Outside.service(this,"gus.b.errors1.watcher");
		exceptionView = Outside.service(this,"*gus.b.dataview1.exception");
		errList = (List) Outside.resource(this,"errlist");
		
		model = new TableModel0();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{selectionChanged();}
		});
		
		numberLabel = new JLabel(" 0");
		
		JPanel rightPane = new JPanel(new BorderLayout());
		rightPane.add((JComponent) exceptionView.i(),BorderLayout.CENTER);
		rightPane.add(numberLabel,BorderLayout.SOUTH);
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setOneTouchExpandable(true);
		split.setDividerLocation(200);
		split.setDividerSize(3);
		
		split.setLeftComponent(new JScrollPane(table));
		split.setRightComponent(rightPane);

		watcher.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	


	public void actionPerformed(ActionEvent e) 
	{refreshGui();}
	
	
	private void refreshGui() 
	{
		try 
		{
			numberLabel.setText(" "+errList.size());
			SwingUtilities.invokeLater(model);
		}
		catch(Exception e) 
		{Outside.err(this,"refreshGui()",e);}
	}
	
	
	
	private void selectionChanged()
	{
		try
		 {
			if(table.getSelectionModel().isSelectionEmpty()) return;
			
			int row = table.getSelectedRow();
			Object[] info = (Object[]) errList.get(row);
			Exception ex = (Exception) info[3];
			
			exceptionView.p(ex);
		}
		catch (Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		public int getColumnCount() {return 5;}
		public int getRowCount() {return errList.size();}
        
		public String getColumnName(int y)
		{
			if(y==0) return "time";
			if(y==1) return "source";
			if(y==2) return "id";
			if(y==3) return "type";
			if(y==4) return "message";
			return null;
		}
        
		public Class getColumnClass(int y)
		{return String.class;}
        
		public boolean isCellEditable(int x, int y)
		{return false;}

		public Object getValueAt(int x, int y)
		{
			Object[] info = (Object[]) errList.get(x);
			
			Entity entity = (Entity) info[0];
			String method = (String) info[1];
			Date date = (Date) info[2];
			Exception e = (Exception) info[3];
            
			if(y==0) return timeStamp(date);  // time stamp
			if(y==1) return sourceName(entity);  // source
			if(y==2) return method; // id
			if(y==3) return e.getClass().getSimpleName();
			if(y==4) return e.getMessage();
			return null;
		}
		
		public void run()
		{fireTableStructureChanged();}
	}
	
	
	private String sourceName(Object source)
	{
		if(source==null) return "null";
		if(source instanceof Class) return "("+((Class) source).getName()+")";
		return source.getClass().getName();
	}

	private String timeStamp(Date d)
	{return new SimpleDateFormat("HH:mm:ss").format(d);}
}
