package a.entity.gus.b.convert1.stringtoborder;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private Service bevel;
	private Service empty;
	private Service etched;
	private Service line;
	private Service lowered;
	private Service raised;
	private Service matte;
	private Service titled;
	private Service oval;


	public EntityImpl() throws Exception
	{
		bevel = Outside.service(this,"gus.b.convert1.stringtoborder.bevel");
		empty = Outside.service(this,"gus.b.convert1.stringtoborder.empty");
		etched = Outside.service(this,"gus.b.convert1.stringtoborder.etched");
		line = Outside.service(this,"gus.b.convert1.stringtoborder.line");
		lowered = Outside.service(this,"gus.b.convert1.stringtoborder.lowered");
		raised = Outside.service(this,"gus.b.convert1.stringtoborder.raised");
		matte = Outside.service(this,"gus.b.convert1.stringtoborder.matte");
		titled = Outside.service(this,"gus.b.convert1.stringtoborder.titled");
		oval = Outside.service(this,"gus.b.convert1.stringtoborder.oval");
	}


	public Object t(Object obj) throws Exception
	{return stringToBorder((String) obj);}
	
	
	
	private Border stringToBorder(String text) throws Exception
	{
		String[] parts = text.split("\\|");
		
		Border[] borders = new Border[parts.length];
		for(int i=0;i<parts.length;i++)
		borders[i] = build(parts[i]);
		
		Border result = null;
		for(int i=0;i<borders.length;i++)
		result = combine(result,borders[i]);
		return result;
	}
	
	
	
	
	private Border build(String text) throws Exception
	{
		String[] n = text.split(" ",2);
		String type = n[0];
		String info = n.length==2?n[1]:null;
		return (Border) find(type).t(info);
	}
	
	
	
	
	private Service find(String type) throws Exception
	{
		if(type.equals("bevelborder")) return bevel;
		if(type.equals("emptyborder")) return empty;
		if(type.equals("etchedborder")) return etched;
		if(type.equals("lineborder")) return line;
		if(type.equals("loweredborder")) return lowered;
		if(type.equals("raisedborder")) return raised;
		if(type.equals("matteborder")) return matte;
		if(type.equals("titledborder")) return titled;
		if(type.equals("ovalborder")) return oval;
		
		throw new Exception("Unknown border type: "+type);
	}
	
	
	
	
	private Border combine(Border border1, Border border2)
	{
		if(border1==null) return border2;
		if(border2==null) return border1;
		return BorderFactory.createCompoundBorder(border1,border2); 
	}
}