package a.entity.gus.a.file.icon.read.imageio;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null || !file.exists()) return null;
		
		BufferedImage img = ImageIO.read(file);
		return new ImageIcon(img);
	}
}