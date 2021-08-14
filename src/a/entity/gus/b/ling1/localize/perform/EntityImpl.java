package a.entity.gus.b.ling1.localize.perform;

import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20210814";}

	private Service findMsg;

	public EntityImpl() throws Exception
	{
		findMsg = Outside.service(this,"gus.b.ling1.msg.find");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		String msg = (String) findMsg.r(key);
	
		if(obj instanceof JMenu) {
			((JMenu) obj).setText(msg);
		}
		else if(obj instanceof JMenuItem) {
			((JMenuItem) obj).setText(msg);
		}
		else if(obj instanceof JLabel) {
			((JLabel) obj).setText(msg);
		}
		else if(obj instanceof AbstractButton) {
			((AbstractButton) obj).setText(msg);
		}
		else if(obj instanceof Action) {
			Action action = (Action) obj;
			action.putValue(Action.NAME,msg);
		}
		else if(obj instanceof JTextComponent) {
			JTextComponent comp = (JTextComponent) obj;
			comp.setText(msg);
			comp.setCaretPosition(0);
		}
		
		else throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
}
