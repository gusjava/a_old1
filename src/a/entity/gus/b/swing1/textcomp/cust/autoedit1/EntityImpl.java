package a.entity.gus.b.swing1.textcomp.cust.autoedit1;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;
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
			String selection = comp.getSelectedText();
			SwingUtilities.invokeLater(new Holder2(comp, selection, e));
		}
	}
	
	
	
	private class Holder2 implements Runnable
	{
		private JTextComponent comp;
		private String selection;
		private KeyEvent evt;
		
		public Holder2(JTextComponent comp, String selection, KeyEvent evt)
		{
			this.comp = comp;
			this.selection = selection;
			this.evt = evt;
		}
		public void run()
		{perform(comp,selection,evt);}
	}
	
	
	


	private void perform(JTextComponent comp, String selection, KeyEvent evt)
	{
		try
		{
			int code = evt.getKeyCode();
			if(isShift(evt))
			{
				switch(code)
				{
					case KeyEvent.VK_TAB: performTabInv.p(new Object[] {comp,selection});break;
				}
			}
			else
			{
				switch(code)
				{
					case KeyEvent.VK_ENTER: performEnter.p(comp);break;
					case KeyEvent.VK_TAB: performTab.p(new Object[] {comp,selection});break;
				}
			}
		}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent, String, KeyEvent)",e);}
	}
	
	
	private boolean isShift(KeyEvent e)
	{return e.getModifiers() == KeyEvent.SHIFT_MASK;}
}
