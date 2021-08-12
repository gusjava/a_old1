package a.entity.gus.b.convert1.stringtocolor.rgb;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	public static Pattern P_INT = Pattern.compile("[0-9]+");
	
	
	public Object t(Object obj) throws Exception
	{return stringToColor((String) obj);}
	
	
	
	private Color stringToColor(String s) throws Exception
	{
		if(s==null) return null;
		if(s.equals("")) return null;
        
		List l = extractInt(s);
		
		if(l.size()==4)
		{
			int r = int_(l.get(0));
			int g = int_(l.get(1));
			int b = int_(l.get(2));
			int a = int_(l.get(3));
			return new Color(r,g,b,a);
		}
		if(l.size()==3)
		{
			int r = int_(l.get(0));
			int g = int_(l.get(1));
			int b = int_(l.get(2));
			return new Color(r,g,b);
		}
		if(l.size()==1)
		{
			int val = int_(l.get(0));
			return new Color(val);
		}
		
		return null;
	}

    
    
	private int int_(Object s)
	{return Integer.parseInt((String) s);}
    
    
	private List extractInt(String s)
	{
		List l = new ArrayList();
		Matcher m = P_INT.matcher(s);
		while(m.find()) l.add(m.group());
		return l;
	}

}