package a.entity.gus.b.dataview1.class1;

import java.net.URL;
import java.net.URLClassLoader;

import a.framework.*;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210826";}


	private Service formPanel;
	
	private Class data;

	public EntityImpl() throws Exception {
		formPanel = Outside.service(this,"*gus.b.swing1.panel.formpanel.string");
	}
	
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public void p(Object obj) throws Exception {
		data = (Class) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	private void resetGui() throws Exception
	{formPanel.e();}
    
    
	
	private void updateGui() throws Exception
	{
		ClassLoader cl = data.getClassLoader();
		
		formPanel.e();
		
		formPanel.v("class",data.getName());
		formPanel.v("class loader",cl!=null ? cl.getClass().getName() : "null");
		
		if(cl instanceof URLClassLoader)
		{
			URLClassLoader urlCl = (URLClassLoader) cl;
			URL[] urls = urlCl.getURLs();
			formPanel.v("url nb", ""+urls.length);
		}
	}
	

	public Object i() throws Exception
	{return formPanel.i();}
}
