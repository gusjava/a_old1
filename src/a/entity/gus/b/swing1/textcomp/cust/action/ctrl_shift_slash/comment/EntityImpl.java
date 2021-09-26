package a.entity.gus.b.swing1.textcomp.cust.action.ctrl_shift_slash.comment;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210926";}

	public static final String KEY = "ctrl shift /";
	
	
	private Service perform;
	private Service stringToKeystroke;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.b.swing1.textcomp.cust.action.ctrl_shift_slash.comment.perform");
		stringToKeystroke = Outside.service(this,"gus.b.convert1.stringtokeystroke");
	}
	
	
	public void p(Object obj) throws Exception
	{new Holder((JTextComponent) obj);}



	private class Holder extends AbstractAction
	{
		private JTextComponent comp;
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			KeyStroke keyStroke = (KeyStroke) stringToKeystroke.t(KEY);
			comp.getInputMap().put(keyStroke, this);
		}
		public void actionPerformed(ActionEvent e)
		{perform(comp);}
	}
	
	
	private void perform(JTextComponent comp)
	{
		try{perform.p(comp);}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent)",e);}
	}
}
