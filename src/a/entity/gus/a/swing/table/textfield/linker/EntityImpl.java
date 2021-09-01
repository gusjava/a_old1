package a.entity.gus.a.swing.table.textfield.linker;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Rectangle;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210901";}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTable table = (JTable) o[0];
		JTextField field = (JTextField) o[1];
		
		new Holder(table,field);
	}
	
	
	private class Holder extends KeyAdapter
	{
		private JTable table;
		private JTextField field;
		
		public Holder(JTable table, JTextField field)
		{
			this.table = table;
			this.field = field;
			
			table.addKeyListener(this);
			
			field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),new AbstractAction() {
				public void actionPerformed(ActionEvent e) {focusTop();}
			});
			field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),new AbstractAction() {
				public void actionPerformed(ActionEvent e) {focusBottom();}
			});
		}
		
		
		public void keyPressed(KeyEvent e)
		{
			int code = e.getKeyCode();
			int nb = table.getModel().getRowCount();
			
			if(code==KeyEvent.VK_UP && table.getSelectedRow()==0) {focusField();return;}
			if(code==KeyEvent.VK_DOWN && table.getSelectedRow()==nb-1) {focusField();return;}
			if(code==KeyEvent.VK_ESCAPE) {focusField();return;}
		}
		
		
		private void focusTop()
		{
			table.requestFocusInWindow();
			int nb = table.getModel().getRowCount();
			if(nb==0) return;
			
			table.getSelectionModel().setSelectionInterval(0,0);
			ensureRowIsVisible(table.getSelectedRow());
		}


		private void focusBottom()
		{
			table.requestFocusInWindow();
			int nb = table.getModel().getRowCount();
			if(nb==0) return;
			
			table.getSelectionModel().setSelectionInterval(nb-1,nb-1);
			ensureRowIsVisible(table.getSelectedRow());
		}


		private void focusField()
		{field.requestFocusInWindow();}
		
		
		private void ensureRowIsVisible(int row)
		{
			Rectangle rect = table.getCellRect(row,0,true);
			table.scrollRectToVisible(rect);
		}
	}
}