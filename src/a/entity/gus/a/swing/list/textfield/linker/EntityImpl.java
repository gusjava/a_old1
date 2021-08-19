package a.entity.gus.a.swing.list.textfield.linker;

import a.framework.*;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210817";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JList list = (JList) o[0];
		JTextField field = (JTextField) o[1];
		
		new Holder(list, field);
	}
	
	
	private class Holder extends KeyAdapter
	{
		private JList list;
		private JTextField field;
		
		public Holder(JList list, JTextField field)
		{
			this.list = list;
			this.field = field;
			
			list.addKeyListener(this);
			
			field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),new AbstractAction() {
				public void actionPerformed(ActionEvent e) {focusListTop();}
			});
			field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),new AbstractAction() {
				public void actionPerformed(ActionEvent e) {focusListBottom();}
			});
		}
		
		
		public void keyPressed(KeyEvent e)
		{
			int code = e.getKeyCode();
			int nb = list.getModel().getSize();
			
			if(code==KeyEvent.VK_UP && list.getSelectedIndex()==0) {focusField();return;}
			if(code==KeyEvent.VK_DOWN && list.getSelectedIndex()==nb-1) {focusField();return;}
			if(code==KeyEvent.VK_ESCAPE) {focusField();return;}
		}
		
		
		private void focusListTop()
		{
			list.requestFocusInWindow();
			int nb = list.getModel().getSize();
			if(nb==0) return;
			
			list.getSelectionModel().setSelectionInterval(0,0);
			list.ensureIndexIsVisible(list.getSelectedIndex());
		}


		private void focusListBottom()
		{
			list.requestFocusInWindow();
			int nb = list.getModel().getSize();
			if(nb==0) return;
			
			list.getSelectionModel().setSelectionInterval(nb-1,nb-1);
			list.ensureIndexIsVisible(list.getSelectedIndex());
		}


		private void focusField()
		{field.requestFocusInWindow();}
	}
}
