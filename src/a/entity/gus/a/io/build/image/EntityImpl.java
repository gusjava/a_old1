package a.entity.gus.a.io.build.image;

import java.io.InputStream;

import javax.imageio.ImageIO;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}


	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		return ImageIO.read(is);
	}
}