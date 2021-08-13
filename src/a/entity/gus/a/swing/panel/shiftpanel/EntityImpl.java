package a.entity.gus.a.swing.panel.shiftpanel;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.SwingUtilities;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210813";}


	private JPanel1 panel;
	private Object current;


	private JComponent currentComp()
	{
		try
		{
			if(current==null) return null;
			if(current instanceof JComponent) return (JComponent) current;
			if(current instanceof I) return (JComponent) ((I)current).i();
		}
		catch(Exception e)
		{Outside.err(this,"currentComp()",e);}
		return null;
	}


	public EntityImpl()
	{panel = new JPanel1();}


	public Object i() throws Exception
	{return panel;}


	public void p(Object obj) throws Exception
	{
		current = obj;
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {panel.updatePanel();}
		});
	}


	private class JPanel1 extends JPanel
	{
		private JScrollPane scroll = new JScrollPane();
		
		public JPanel1() {super(new BorderLayout());}

		public void updatePanel()
		{
			removeAll();

			JComponent comp = currentComp();
			if(comp!=null)
			{
				if(comp instanceof Scrollable)
				{
					scroll.setViewportView(comp);
					add(scroll,BorderLayout.CENTER);
				}
				else add(comp,BorderLayout.CENTER);
			}
			
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{
				validate();
				repaint();
			}
		}
	}
}