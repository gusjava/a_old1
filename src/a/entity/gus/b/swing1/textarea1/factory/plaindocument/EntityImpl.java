package a.entity.gus.b.swing1.textarea1.factory.plaindocument;

import a.framework.*;
import java.awt.event.ActionListener;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import javax.swing.event.UndoableEditEvent;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210911";}
	
	
	public Object g() throws Exception
	{return new PlainDocument1();}
	
	
	
	public class PlainDocument1 extends PlainDocument implements V
	{
		private Object undoableHandler;
		private ActionListener listener;
	
		public PlainDocument1()
		{
			super();
			listener = new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{handleUndoableEditEvent();}
			};
		}
	
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("undoableHandler"))
			{initUndoableHandler(obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		private void initUndoableHandler(Object undoableHandler_)
		{
			resetUndoableHandler();
			if(undoableHandler_==null) return;
			
			undoableHandler = undoableHandler_;
			addActionListener((S) undoableHandler, listener);
		}
		
		private void resetUndoableHandler()
		{
			if(undoableHandler==null) return;
			removeActionListener((S) undoableHandler, listener);
			undoableHandler = null;
		}
		
		private void handleUndoableEditEvent()
		{
			UndoableEditEvent evt = (UndoableEditEvent) g((G)undoableHandler);
			if(evt!=null) super.fireUndoableEditUpdate(evt);
		}
		
		protected void fireUndoableEditUpdate(UndoableEditEvent evt)
		{
			if(!f((F)undoableHandler,evt))
			super.fireUndoableEditUpdate(evt);
		}
	}
	
	
	
	private void addActionListener(S s, ActionListener listener) {
		try{s.addActionListener(listener);}
		catch(Exception e) {Outside.err(this,"addActionListener(S,ActionListener)",e);}
	}
	
	private void removeActionListener(S s, ActionListener listener) {
		try{s.addActionListener(listener);}
		catch(Exception e) {Outside.err(this,"removeActionListener(S,ActionListener)",e);}
	}
	
	private Object g(G g)
	{
		if(g==null) return null;
		try{return g.g();}
		catch(Exception e) {Outside.err(this,"g(G)",e);}
		return null;
	}
	
	private boolean f(F f, Object obj)
	{
		if(f==null) return false;
		try{return f.f(obj);}
		catch(Exception e) {Outside.err(this,"f(F,Object)",e);}
		return false;
	}
}
