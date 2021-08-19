package a.entity.gus.a.swing.textcomp.cust.action.escap.clear;

import a.framework.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210818";}

	public static final KeyStroke ESCAPE = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
	
	
	public void p(Object obj) throws Exception
	{new Holder((JTextComponent) obj);}


	private class Holder extends AbstractAction
	{
		private JTextComponent comp;
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			comp.getInputMap().put(ESCAPE,this);
		}

		public void actionPerformed(ActionEvent e)
		{clear();}

		private void clear()
		{comp.setText("");}
	}
}
