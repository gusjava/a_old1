package a.entity.gus.b.convert1.stringtocolor.html;

import java.awt.Color;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	public Object t(Object obj) throws Exception
	{return htmlToColor((String) obj);}

	
	
	private Color htmlToColor(String s) throws Exception
	{
		if(s==null) return null;
		if(s.equals("")) return null;
		
		if(s.startsWith("#")) s = s.substring(1);
		
		try
		{
			if(s.length()==3) return buildColor3(s);
			if(s.length()==6) return buildColor6(s);
		}
		catch(NumberFormatException e){}
		return null;
	}

	
	
	private Color buildColor3(String s)
	{
		String r_ = s.substring(0,1);
		String g_ = s.substring(1,2);
		String b_ = s.substring(2,3);
		
		int r = Integer.parseInt(r_+r_,16);
		int g = Integer.parseInt(g_+g_,16);
		int b = Integer.parseInt(b_+b_,16);
		return new Color(r,g,b);
	}
	
	
	private Color buildColor6(String s)
	{
		String r_ = s.substring(0,2);
		String g_ = s.substring(2,4);
		String b_ = s.substring(4,6);
		
		int r = Integer.parseInt(r_,16);
		int g = Integer.parseInt(g_,16);
		int b = Integer.parseInt(b_,16);
		return new Color(r,g,b);
	}
}
