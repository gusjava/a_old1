package a.entity.gus.b.appview1.entryview.panel.gif;

import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210813";}


	private Service isToByteArray;
	private JLabel label;
	
	public EntityImpl() throws Exception
	{
		isToByteArray = Outside.service(this,"gus.a.io.transfer.tobytearray");
		label = new JLabel(" ");
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	public void p(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		byte[] data = (byte[]) isToByteArray.t(is);
		label.setIcon(new ImageIcon(data));
	}
}