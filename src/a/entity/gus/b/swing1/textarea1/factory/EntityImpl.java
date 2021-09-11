package a.entity.gus.b.swing1.textarea1.factory;

import a.framework.*;
import javax.swing.JTextArea;
import java.awt.Graphics;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20210911";}


	private Service documentFactory;
	private Service undoableDelayBuilder;

	
	public EntityImpl() throws Exception
	{
		documentFactory = Outside.service(this,"gus.b.swing1.textarea1.factory.plaindocument");
		undoableDelayBuilder = Outside.service(this,"gus.b.swing1.textarea1.plaindocument.undoable.delaybuilder");
	}

	
	
	public Object i() throws Exception
	{
		PlainDocument doc = (PlainDocument) documentFactory.g();
		return new JTextArea1(doc);
	}



	private class JTextArea1 extends JTextArea implements P, V, R
	{
		private PlainDocument doc;
		private E undoable;
		
		private List<P> l1 = new ArrayList<P>();
		private List<T> l2 = new ArrayList<T>();
		private Map data = new HashMap();
		
        	public JTextArea1(PlainDocument doc)
		{
			super(doc);
			this.doc = doc;
			setOpaque(false);
		}
		
		
		
		public void p(Object obj) throws Exception
		{l1.add((P) obj);}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("painter")) {l1.add((P) obj);return;}
			if(key.equals("formater")) {l2.add((T) obj);return;}
			if(key.equals("undoable")) {initUndoable(toBool(obj));return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("data")) return data;
			if(key.equals("keys")) return new String[]{"data"};
			
			throw new Exception("Unknown key: "+key);
		}


		protected void paintComponent(Graphics g)
		{
			g.setColor(getBackground());
			g.fillRect(0,0,getWidth(),getHeight());
			for(P p:l1) handle(p,g,this);
            		super.paintComponent(g);
		}
		
		
		public void repaint(long tm, int x, int y, int w, int h)
		{super.repaint(tm,0,0,getWidth(),getHeight());}
		
		
		
		
		public void setText(String text)
		{
			initUndoable(true);
			
			getHighlighter().removeAllHighlights();
			
			for(T t:l2) text = format(t,text);
            		super.setText(text);

			initUndoable(false);
		}
		
		
		public void replaceSelection(String text)
		{
			initUndoable(true);
			super.replaceSelection(text);
			initUndoable(false);
		}
		
		
		
		private void initUndoable(boolean val)
		{
			if(val) undoable = getUndoable(doc);
			else
			{
				execute(undoable);
				undoable = null;
			}
		}
		
		private boolean toBool(Object obj)
		{return Boolean.parseBoolean((String) obj);}
	}




	private void handle(P p, Graphics g, JTextArea comp)
	{
		try{p.p(new Object[]{g,comp});}
		catch(Exception e) {Outside.err(this,"handle(P,Graphics,JTextArea)",e);}
	}
	
	
	private String format(T t, String text)
	{
		try{return (String) t.t(text);}
		catch(Exception e) {Outside.err(this,"format(T,String)",e);}
		return text;
	}
	
	private E getUndoable(Object doc)
	{
		try{return (E) undoableDelayBuilder.t(doc);}
		catch(Exception e){Outside.err(this,"getUndoable(Object)",e);}
		return null;
	}
	
	private void execute(E e)
	{
		if(e==null) return;
		try{e.e();}
		catch(Exception ex)
		{Outside.err(this,"execute(E)",ex);}
	}
}
