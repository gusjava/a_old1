package a.entity.gus.b.swing1.textarea1.plaindocument.undoable.delaybuilder;

import a.framework.*;
import javax.swing.text.PlainDocument;
import javax.swing.undo.CompoundEdit;
import javax.swing.event.UndoableEditEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210911";}


	
	
	public Object t(Object obj) throws Exception
	{return new Holder((PlainDocument) obj);}
	
	
	
	private class Holder extends S1 implements E, G, F
	{
		private PlainDocument doc;
		private CompoundEdit edit;
		
		public Holder(PlainDocument doc) throws Exception
		{
			this.doc = doc;
			edit = new CompoundEdit();
			((V)doc).v("undoableHandler",this);
		}
		
		public void e() throws Exception
		{
			checkClosed();
			fireUndoable();
			close();
		}
		
		public Object g() throws Exception
		{
			checkClosed();
			
			edit.end();
			UndoableEditEvent evt = new UndoableEditEvent(doc,edit);
			edit = new CompoundEdit();
			return evt;
		}
		
		public boolean f(Object obj) throws Exception
		{
			checkClosed();
			
			UndoableEditEvent evt = (UndoableEditEvent) obj;
			edit.addEdit(evt.getEdit());
			return true;
		}
		
		private void fireUndoable()
		{send(this,"fireUndoable()");}
		
		
		private void close() throws Exception
		{
			((V)doc).v("undoableHandler",null);
			doc = null;
			edit = null;
		}
		
		private void checkClosed() throws Exception
		{if(doc==null) throw new Exception("Attempt to use a delay builder which has already been closed");}
	}
}
