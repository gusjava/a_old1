package a.entity.gus.b.convert1.stringtoobject.convertor1;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private Service stringToBorder;
	private Service stringToColor;
	private Service stringToFont;

	public EntityImpl() throws Exception
	{
		stringToBorder = Outside.service(this,"gus.b.convert1.stringtoborder");
		stringToColor = Outside.service(this,"gus.b.convert1.stringtocolor");
		stringToFont = Outside.service(this,"gus.b.convert1.stringtofont");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String)obj;
		String[] n = s.split("#",2);
		return find(n[0]).t(n[1]);
	}
	
	private T find(String s) throws Exception
	{
		if(s.equals("border")) return stringToBorder;
		if(s.equals("color")) return stringToColor;
		if(s.equals("font")) return stringToFont;
		
		throw new Exception("Unknown convertor type: "+s);
	}
}