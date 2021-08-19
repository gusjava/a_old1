package a.entity.gus.b.swing1.textcomp.build.delayholder;

import java.util.Map;
import java.util.HashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210818";}

	public static final long DELAY = 200;
	
	private Service timer;
	private Map map;
	
	public EntityImpl() throws Exception
	{
		timer = Outside.service(this,"gus.b.timer1.register");
		map = new HashMap();
	}
	
	public Object t(Object obj) throws Exception
	{
		if(!map.containsKey(obj)) map.put(obj,new TextCompHolder((JTextComponent) obj));
		return map.get(obj);
	}
	
	
	
	private class TextCompHolder extends S1 implements DocumentListener, P, G, E, Runnable
	{
		private JTextComponent comp;
		private boolean activated = true;
		private E delayer;
		
		public TextCompHolder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			comp.getDocument().addDocumentListener(this);
			delayer = (E) timer.t(new Object[] {DELAY, this});
		}
		
		public void changedUpdate(DocumentEvent e) {}
		public void insertUpdate(DocumentEvent e) {textChanged_();}
		public void removeUpdate(DocumentEvent e) {textChanged_();}
		
		
		private void textChanged_()
		{execute(delayer);}
		
		
		public void e() throws Exception
		{SwingUtilities.invokeLater(this);}
		
		
		public void run()
		{textChanged();}
		
		
		private void textChanged()
		{send(this,"textChanged()");}
		
		
		public Object g() throws Exception
		{return comp;}
		

		public void p(Object obj) throws Exception
		{
			if(obj.equals("activate") && !activated)
			{
				activated = true;
				comp.getDocument().addDocumentListener(this);
			}
			else if(obj.equals("disactivate") && activated)
			{
				activated = false;
				comp.getDocument().removeDocumentListener(this);
			}
		}
	}
	
	
	private void execute(E exe) {
		try {exe.e();}
		catch(Exception e) {Outside.err(this,"execute(E)",e);}
	}
}
