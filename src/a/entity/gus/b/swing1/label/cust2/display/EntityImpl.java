package a.entity.gus.b.swing1.label.cust2.display;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20210822";}

	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus.b.icons1.provider");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JLabel) obj);}
	
	
	private void perform(String info, JLabel label) throws Exception
	{
		if(info==null || info.equals(""))
		{
			label.setText(" ");
			label.setIcon(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			label.setText(m[1]);
			label.setIcon(icon(m[0]));
		}
		else
		{
			label.setText(info);
			label.setIcon(null);
		}
	}
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
}
