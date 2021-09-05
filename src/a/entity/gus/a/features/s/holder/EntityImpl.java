package a.entity.gus.a.features.s.holder;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, P, G, ActionListener {
	public String creationDate() {return "20210905";}

	private S s;
	
	public void p(Object obj) throws Exception
	{
		if(s!=null) s.removeActionListener(this);
		s = null;
	
		if(obj!=null && obj instanceof S)
		{
			s = (S) obj;
			s.addActionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		send(this,s);
	}

	public Object g() throws Exception
	{
		return s;
	}
}
