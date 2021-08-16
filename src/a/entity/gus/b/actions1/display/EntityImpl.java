package a.entity.gus.b.actions1.display;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.Action;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20210816";}


	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus.b.icons1.provider");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key, (Action) obj);}
	
	
	private void perform(String info, Action action) throws Exception
	{
		if(info==null || info.equals(""))
		{
			action.putValue(Action.NAME,"");
			action.putValue(Action.SMALL_ICON,null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			action.putValue(Action.NAME,m[1]);
			action.putValue(Action.SHORT_DESCRIPTION,m[1]);
			action.putValue(Action.SMALL_ICON,icon(m[0]));
		}
		else
		{
			action.putValue(Action.NAME,info);
			action.putValue(Action.SHORT_DESCRIPTION,info);
			action.putValue(Action.SMALL_ICON,null);
		}
	}
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
}
