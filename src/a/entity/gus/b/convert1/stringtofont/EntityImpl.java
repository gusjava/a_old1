package a.entity.gus.b.convert1.stringtofont;

import a.framework.*;
import java.awt.Font;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	public static final int DEFAULT_STYLE = 0;
	public static final int DEFAULT_SIZE = 12;


	private Service availableNames;

	public EntityImpl() throws Exception
	{
		availableNames = Outside.service(this,"gus.a.font.availablefontnames.list");
	}



	public Object t(Object obj) throws Exception
	{return stringToFont((String) obj);}
	
	
	
	private Font stringToFont(String s) throws Exception
 	{
		List avail = (List) availableNames.g();
		if(avail.contains(s)) return new Font(s,DEFAULT_STYLE,DEFAULT_SIZE);
		
		s = s.toUpperCase();
		String[] n = s.split(" ");
		
		int len = n.length;
		if(len>3) throw new Exception("Invalid syntax: "+s);
        
		String name = n[0];
		int style = len>1 ? findStyle(n[1]) : DEFAULT_STYLE;
		int size = len>2 ? Integer.parseInt(n[2]) : DEFAULT_SIZE;
        
		return new Font(name,style,size);
	}
    
    

	private int findStyle(String info)
	{
		if(info.toLowerCase().equals("plain")) return 0;
		if(info.toLowerCase().equals("bold")) return 1;
		if(info.toLowerCase().equals("italic")) return 2;
		if(info.toLowerCase().equals("bold|italic")) return 3;
		if(info.toLowerCase().equals("italic|bold")) return 3;
		return Integer.parseInt(info);
	}
}