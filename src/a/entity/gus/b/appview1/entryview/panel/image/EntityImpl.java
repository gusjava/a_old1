package a.entity.gus.b.appview1.entryview.panel.image;

import java.io.InputStream;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210813";}


	private Service screen;
	private Service toImage;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus.a.swing.panel.imagepanel.fit");
		toImage = Outside.service(this,"gus.a.io.transfer.toimage");
	}
	
	
	public Object i() throws Exception
	{return screen.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		screen.p(toImage.t(is));
	}
}