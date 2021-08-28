package a.entity.gus.a.swing.toolbar.factory1;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import a.framework.Entity;
import a.framework.I;
import a.framework.T;

public class EntityImpl implements Entity, I, T {
	public String creationDate() {return "20210828";}
	
	public static Border BORDER = BorderFactory.createEmptyBorder();
	public static Dimension DIM = new Dimension(20,20);
	
	
	public Object i() throws Exception
	{return new JToolBar1();}
	
	
	public Object t(Object obj) throws Exception
	{
		JToolBar1 bar = new JToolBar1();
		bar.add((Action) obj);
		return bar;
	}
	
	
	
	private class JToolBar1 extends JToolBar
	{
		public JToolBar1()
		{
			super();
			setFloatable(false);
			setFocusable(true);
			setBorder(BORDER);
			
			addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e)
				{requestFocusInWindow();}
			});
		}
		
		public JButton add(Action a)
		{
			JButton b = super.add(a);
			b.setMinimumSize(DIM);
			b.setMaximumSize(DIM);
			b.setBorder(BORDER);
			setMinimumSize(new Dimension(0,0));
			b.setOpaque(false);
			return b;
		}
	}
}