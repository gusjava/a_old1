package a.entity.gus.b.find1.dimension;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.util.List;

import javax.swing.JFrame;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


private Service stringToDimension;
	
	public EntityImpl() throws Exception
	{
		stringToDimension = Outside.service(this,"gus.b.convert1.stringtodimension");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Dimension) return obj;
		
		if(obj instanceof int[]) return intArrayToDim((int[]) obj);
		if(obj instanceof List) return listToDim((List) obj);
		if(obj instanceof String) return stringToDimension.t(obj);
		if(obj instanceof Point) return pointToDim((Point) obj);
		if(obj instanceof JFrame) return jframeToDim((JFrame) obj);
		if(obj instanceof Rectangle) return rectangleToDim((Rectangle) obj);
		if(obj instanceof RenderedImage) return imageToDim((RenderedImage) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Dimension intArrayToDim(int[] v)
	{return new Dimension(v[0],v[1]);}
	
	private Dimension listToDim(List list)
	{return new Dimension(toInt(list,0),toInt(list,1));}
	
	private Dimension pointToDim(Point v)
	{return new Dimension(v.x,v.y);}
	
	private Dimension imageToDim(RenderedImage v)
	{return new Dimension(v.getWidth(),v.getHeight());}
	
	private Dimension jframeToDim(JFrame v)
	{return new Dimension(v.getWidth(),v.getHeight());}
	
	private Dimension rectangleToDim(Rectangle v)
	{return new Dimension(v.width,v.height);}
	
	
	
	private int toInt(List list, int index)
	{
		Object o = list.get(index);
		return Integer.parseInt(""+o);
	}
}
