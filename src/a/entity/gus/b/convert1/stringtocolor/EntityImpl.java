package a.entity.gus.b.convert1.stringtocolor;

import java.awt.Color;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private Service htmlToColor;
	private Service nameToColor;
	private Service rgbToColor;

	public EntityImpl() throws Exception
	{
		htmlToColor = Outside.service(this,"gus.b.convert1.stringtocolor.html");
		nameToColor = Outside.service(this,"gus.b.convert1.stringtocolor.name");
		rgbToColor = Outside.service(this,"gus.b.convert1.stringtocolor.rgb");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		if(s.equals("")) return null;
		
		Color c1 = (Color) htmlToColor.t(s);
		if(c1!=null) return c1;
        
		Color c2 = (Color) nameToColor.t(s);
		if(c2!=null) return c2;
        
		Color c3 = (Color) rgbToColor.t(s);
		if(c3!=null) return c3;
        
		return null;
	}
}