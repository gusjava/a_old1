package a.entity.gus.b.images1.bufferedimage.create;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private Service toDim;
	
	public EntityImpl() throws Exception
	{toDim = Outside.service(this,"gus.b.convert1.stringtodimension");}

	
	
	public Object t(Object obj) throws Exception
	{
		Dimension dim = (Dimension) toDim.t(obj);
		return graphicsConf().createCompatibleImage(dim.width, dim.height, Transparency.BITMASK);
	}
	
	
	private GraphicsEnvironment graphicsEnv()
	{return GraphicsEnvironment.getLocalGraphicsEnvironment();}
	
	private GraphicsDevice graphicsDevice()
	{return graphicsEnv().getDefaultScreenDevice();}
	
	private GraphicsConfiguration graphicsConf()
	{return graphicsDevice().getDefaultConfiguration();}
}