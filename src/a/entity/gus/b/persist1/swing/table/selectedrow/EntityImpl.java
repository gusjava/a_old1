package a.entity.gus.b.persist1.swing.table.selectedrow;

import javax.swing.JTable;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20210907";}

	private Service manager;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus.b.persist1.manager");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		final JTable comp = (JTable) obj;
		
		String text = (String) manager.r(key);
		if(isInt(text))
		{
			int index = toInt(text);
			comp.getSelectionModel().setSelectionInterval(index, index);
		}
		
		manager.v(key,new G(){
			public Object g() throws Exception {return ""+comp.getSelectedRow();}
		});
	}
	
	
	private boolean isInt(String s)
	{
		if(s==null) return false;
		try{Integer.parseInt(s);}
		catch(NumberFormatException e) {return false;}
		return true;
	}
	
	private int toInt(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e) {return 0;}
	}
}
