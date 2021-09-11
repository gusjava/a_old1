package a.entity.gus.b.swing1.textarea.buildscrollpane.linenb;

import a.framework.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210911";}

	public static final Color COLOR_BACKGROUND = new Color(240,240,240);
	public static final Color COLOR_FOREGROUND = new Color(153,153,153);


	private Service clipboard;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.a.clipboard.string");
	}

	

	public Object t(Object obj) throws Exception
	{return new JScrollPane1((JTextComponent) obj);}



	private class JScrollPane1 extends JScrollPane implements DocumentListener, CaretListener, PropertyChangeListener, MouseListener
	{
		private JTextComponent comp;
		private SimpleAttributeSet attr_p;
		private SimpleAttributeSet attr_b;
		private JTextPane lines;
		
		private int lineNb = -1;
		private int indexCaret = -1;
		
		private Thread t1;
		private Thread t2;
		
		public JScrollPane1(JTextComponent comp)
		{
			super(comp);
			this.comp = comp;
			
			attr_p = new SimpleAttributeSet();
			attr_b = new SimpleAttributeSet();
			StyleConstants.setBold(attr_b,true);
			StyleConstants.setForeground(attr_b,Color.BLACK);
			
			lines = new JTextPane();
			lines.setBackground(COLOR_BACKGROUND);
			lines.setForeground(COLOR_FOREGROUND);
			lines.setEditable(false);
			lines.setFocusable(false);
			lines.addMouseListener(this);
			
			setRowHeaderView(lines);

			comp.getDocument().addDocumentListener(this);
			comp.addCaretListener(this);
			comp.addPropertyChangeListener(this);
			
			performUpdate();
		}

		public void changedUpdate(DocumentEvent de)
		{changed();}
 
		public void insertUpdate(DocumentEvent de)
		{changed();}
 
		public void removeUpdate(DocumentEvent de)
		{changed();}

		public void caretUpdate(CaretEvent e)
		{changed();}
		
		
		public void propertyChange(PropertyChangeEvent evt)
		{lines.setFont(comp.getFont());}
		
		
		public void mouseClicked(MouseEvent e)
		{if(e.getClickCount()==2) copyCurrentIndex(indexCaret);}
		
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
		
		
		private void changed()
		{
			if(t1!=null && t1.isAlive()) return;
			
			Runnable r = this::pendingUpdate;
			t1 = new Thread(r,"THREAD1_"+getClass().getName());
			t1.start();
		}
		
		
		private void pendingUpdate()
		{
			if(t2!=null && t2.isAlive())
			{
				try{t2.join();}
				catch(InterruptedException e){}
			}
			
			Runnable r = this::performUpdate;
			t2 = new Thread(r,"THREAD2_"+getClass().getName());
			t2.start();
		}
		
		
		private void performUpdate()
		{
			lines.setFont(comp.getFont());
			lines.setMargin(comp.getMargin());
			
			Element root = comp.getDocument().getDefaultRootElement();
			
			int newLineNb = root.getElementCount();
			int newIndexCaret = root.getElementIndex(comp.getCaretPosition());
			if(lineNb == newLineNb && indexCaret == newIndexCaret) return;
			
			int maxLength = (""+newLineNb).length();
			
			if(lineNb != newLineNb)
			{
				StringBuffer b = new StringBuffer();
				for(int i=1;i<=newLineNb;i++)
				{
					String i1 = ""+i;
					int d = maxLength-i1.length();
					for(int j=0;j<d;j++) b.append(" ");
					b.append(i1);
					b.append("\n");
				}
				lines.setText(b.toString());
				lines.getStyledDocument().setCharacterAttributes(0, b.length(), attr_p, true);
			}
			else
			{
				int offset = (maxLength+1)*indexCaret;
				lines.getStyledDocument().setCharacterAttributes(offset, maxLength, attr_p, true);
			}
			
			int newOffset = (maxLength+1)*newIndexCaret;
			lines.getStyledDocument().setCharacterAttributes(newOffset, maxLength, attr_b, true);
			
			lineNb = newLineNb;
			indexCaret = newIndexCaret;
		}
	}
	
	
	
	
	private void copyCurrentIndex(int indexCaret)
	{
		try
		{clipboard.p(""+(indexCaret+1));}
		catch(Exception e)
		{Outside.err(this,"copyCurrentIndex(int)",e);}
	}
}