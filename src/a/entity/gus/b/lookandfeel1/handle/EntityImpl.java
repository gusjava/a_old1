package a.entity.gus.b.lookandfeel1.handle;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.border.Border;
import javax.swing.plaf.*;

public class EntityImpl implements Entity, P, V {
	public String creationDate() {return "20210812";}


	private Service convertor;

	public EntityImpl() throws Exception
	{convertor = Outside.service(this,"gus.b.convert1.stringtoobject.convertor1");}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			handleKey(key,map.get(key));
		}
	}
	
	
	public void v(String key, Object obj) throws Exception
	{handleKey(key,obj);}
	
	
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer) obj).intValue();
		if(obj instanceof String) return Integer.parseInt((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
		
		
	private void handleKey(String key, Object obj) throws Exception
	{
		if(key.equals("tooltip.delay"))
		{ToolTipManager.sharedInstance().setInitialDelay(toInt(obj));return;}
    	
		if(key.equals("tooltip.initialdelay"))
		{ToolTipManager.sharedInstance().setInitialDelay(toInt(obj));return;}
    	
		if(key.equals("tooltip.dismissdelay"))
		{ToolTipManager.sharedInstance().setDismissDelay(toInt(obj));return;}
    	
		UIManager.put(key,buildUIResource((String) obj));
	}
	

	
	private UIResource buildUIResource(String value) throws Exception
	{
		Object obj = convertor.t(value);
	
		if(obj instanceof Border) return new BorderUIResource((Border) obj);
		if(obj instanceof Color) return new ColorUIResource((Color) obj);
		if(obj instanceof Font) return new FontUIResource((Font) obj);
		if(obj instanceof Icon) return new IconUIResource((Icon) obj);
        
		throw new Exception("Unknown UIResource for data type: "+obj.getClass().getName());
	}
}