package a.entity.gus.b.dataview1.array;

import java.util.Arrays;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210813";}


	private Service listViewer;
	private Object[] data;

	
	public EntityImpl() throws Exception
	{listViewer = Outside.service(this,"*gus.b.dataview1.list");}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return listViewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Object[]) obj;
		if(data==null) listViewer.p(null);
		else listViewer.p(Arrays.asList(data));
	}
}