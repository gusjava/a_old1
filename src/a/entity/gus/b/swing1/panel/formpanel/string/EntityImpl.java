package a.entity.gus.b.swing1.panel.formpanel.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import a.framework.E;
import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, I, E, V, R, P {
	public String creationDate() {return "20210813";}


	private Service panel;
	
	public EntityImpl() throws Exception
	{
		panel = Outside.service(this,"*gus.a.swing.panel.formpanel");
	}
	
	
	
	public Object i() throws Exception
	{return panel.i();}
	
	
	public void e() throws Exception
	{panel.e();}
	
	
	public void v(String key, Object obj) throws Exception
	{handle(key,""+obj);}
	
	
	public Object r(String key) throws Exception
	{return handle(key,"");}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof String) 
		{
			if(obj.equals("sep")) addSeparator();
			return;
		}
		if(obj instanceof Map)
		{
			addMap((Map) obj);
			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void addMap(Map map) throws Exception
	{
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			String value = ""+map.get(key);
			handle(key,value);
		}
	}
	
	
	private void addSeparator() throws Exception
	{handle(" "," ");}
	
	
	
	private JLabel handle(String key, String value) throws Exception
	{
		JLabel label = new JLabel(value);
//		custLabel.p(label);
		panel.v(key,label);
		return label;
	}
}