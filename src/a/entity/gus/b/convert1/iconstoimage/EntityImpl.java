package a.entity.gus.b.convert1.iconstoimage;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private Service createImage;
	
	public EntityImpl() throws Exception
	{createImage = Outside.service(this,"gus.b.images1.bufferedimage.create");}
	
	
	public Object t(Object obj) throws Exception
	{return iconsToImage((Icon[]) obj);}
	
	
	
	private Image iconsToImage(Icon[] icons) throws Exception
	{
		if(icons==null) return null;
		
		int w = 0;
		int h = 0;
		
		for(Icon icon:icons) if(icon!=null)
		{
			w = Math.max(w,icon.getIconWidth());
			h = Math.max(h,icon.getIconHeight());
		}
		
		BufferedImage image = createImage(w,h);
		Graphics2D g = image.createGraphics();
		
		for(Icon icon:icons) if(icon!=null)
		icon.paintIcon(null,g,0,0);
		
		g.dispose();
		return image;
	}
	
	
	
	private BufferedImage createImage(int w, int h) throws Exception
	{return (BufferedImage) createImage.t(new int[]{w,h});}
}