package a.entity.gus.b.dataview1.object.panel.info;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210811";}

	
	private Service formPanel;
	
 	private Object data;

	public EntityImpl() throws Exception
	{formPanel = Outside.service(this,"*gus.b.swing1.panel.formpanel.string");}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = obj;
		if(data==null) resetGui();
		else updateGui();
	}
		
		
		
		
		
	private void resetGui() throws Exception
	{formPanel.e();}
    
    
	
	private void updateGui() throws Exception
	{
		formPanel.e();
		
		formPanel.v("class name",className(data));
		formPanel.v("toString",toString(data));
		formPanel.v("hashCode",hashCode(data));
	}
    



	private String className(Object obj)
	{
		if(obj==null) return "null";
		return obj.getClass().getName();
	}	
	
	private String toString(Object obj)
	{
		if(obj==null) return "null";
		String s = obj.toString();
		if(s.length()<200) return s;
		return s.substring(0,200)+" ...";
	}
		
	private String hashCode(Object obj)
	{
		if(obj==null) return "null";
		return ""+obj.hashCode();
	}
}