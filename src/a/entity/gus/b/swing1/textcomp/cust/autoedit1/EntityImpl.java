package a.entity.gus.b.swing1.textcomp.cust.autoedit1;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210926";}

	private Service performEnter;
	private Service performTab;
	private Service performTabInv;
	
	public EntityImpl() throws Exception
	{
		performEnter = Outside.service(this,"gus.b.swing1.textcomp.cust.autoedit1.enter.perform");
		performTab = Outside.service(this,"gus.b.swing1.textcomp.cust.autoedit1.tab.perform");
		performTabInv = Outside.service(this,"gus.b.swing1.textcomp.cust.autoedit1.tab_inv.perform");
	}



	public void p(Object obj) throws Exception
	{new Holder1((JTextComponent) obj);}




	private class Holder1 implements KeyListener
	{
		private JTextComponent comp;
		public Holder1(JTextComponent comp)
		{
			this.comp = comp;
			comp.addKeyListener(this);
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e)
		{
			int code = e.getKeyCode();
			if(isShift(e))
			{
				switch(code)
				{
					case KeyEvent.VK_TAB: perform(performTabInv,comp);break;
				}
			}
			else
			{
				switch(code)
				{
					case KeyEvent.VK_ENTER: perform(performEnter,comp);break;
					case KeyEvent.VK_TAB: perform(performTab,comp);break;
				}
			}
		}
	}
	
	private void perform(P p, JComponent comp)
	{
		try{p.p(comp);}
		catch(Exception e)
		{Outside.err(this,"perform(P,JTextComponent)",e);}
	}
	
	private boolean isShift(KeyEvent e)
	{return e.getModifiers() == KeyEvent.SHIFT_MASK;}
}
