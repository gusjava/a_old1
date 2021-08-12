package a.entity.gus.b.convert1.iconstoicon;

import java.awt.Image;

import javax.swing.ImageIcon;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	private Service iconsToImage;

	public EntityImpl() throws Exception
	{
		iconsToImage = Outside.service(this,"gus.b.convert1.iconstoimage");
	}
	
	public Object t(Object obj) throws Exception
	{
		Image image = (Image) iconsToImage.t(obj);
		return new ImageIcon(image);
	}
}