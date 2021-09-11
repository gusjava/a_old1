package a.entity.gus.b.swing1.textcomp.cust.action.ctrl_zy.undoredo;

import a.framework.*;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;
import javax.swing.AbstractAction;

public class EntityImpl implements Entity, P, T {
	public String creationDate() {return "20210911";}
    
	public static final KeyStroke KEYSTROKE_UNDO = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK);
	public static final KeyStroke KEYSTROKE_REDO = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK);
	
	
	public Object t(Object obj) throws Exception
	{return new UndoManager0((JTextComponent) obj);}

	public void p(Object obj) throws Exception
	{t(obj);}



	private class UndoManager0 extends UndoManager implements R
	{
		private JTextComponent comp;
	
		private Action actionUndo;
		private Action actionRedo;
 		private Action actionPurge;

		public UndoManager0(JTextComponent comp)
		{
			super();
			this.comp = comp;
			comp.getDocument().addUndoableEditListener(this);
	    
			initActions();
			update();
		}
	
		private void initActions()
		{
			actionUndo = new AbstractAction() {
				public void actionPerformed(ActionEvent e){undo();}
			};
	    
	    		actionRedo = new AbstractAction() {
				public void actionPerformed(ActionEvent e){redo();}
			};
	    
			actionPurge = new AbstractAction() {
				public void actionPerformed(ActionEvent e){discardAllEdits();}
			};
	    
			comp.getInputMap().put(KEYSTROKE_UNDO,actionUndo);
			comp.getInputMap().put(KEYSTROKE_REDO,actionRedo);
		}

		public void undoableEditHappened(UndoableEditEvent evt)
		{
			super.undoableEditHappened(evt);
			update();
		}
	
		public void discardAllEdits()
       		{
			super.discardAllEdits();
			update();
		}
	
		public void undo()
		{
			if(!canUndo()) return;
			super.undo();
			update();
		}
	
		public void redo()
		{
			if(!canRedo()) return;
			super.redo();
			update();
		}

		private void update()
		{
			actionUndo.setEnabled(canUndo());
			actionRedo.setEnabled(canRedo());
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("actionUndo")) return actionUndo;
			if(key.equals("actionRedo")) return actionRedo;
			if(key.equals("actionPurge")) return actionPurge;
			
			if(key.equals("keys"))
			return new String[]{"actionUndo","actionRedo","actionPurge"};
			
			throw new Exception("Unknown key: "+key);
		}
	}
}