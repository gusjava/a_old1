package a.entity.gus.b.persist1.swing.table.selectedrow;

import java.awt.Rectangle;

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
		setSelectedIndex(comp, text);
		
		manager.v(key,new G(){
			public Object g() throws Exception {return ""+comp.getSelectedRow();}
		});
	}
	
	
	private void setSelectedIndex(JTable comp, String text)
	{
		if(!isInt(text)) return;
		
		int number = comp.getRowCount();
		if(number==0) return;
		
		int index = toInt(text);
		if(index<0) index = 0;
		if(index>=number) index = number-1;

		comp.getSelectionModel().setSelectionInterval(index, index);
        
        Rectangle rect = comp.getCellRect(index,0,true);
		comp.scrollRectToVisible(rect);
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
