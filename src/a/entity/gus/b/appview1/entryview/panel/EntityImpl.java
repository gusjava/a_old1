package a.entity.gus.b.appview1.entryview.panel;

import a.framework.*;

public class EntityImpl implements Entity, I, V {
	public String creationDate() {return "20210813";}


	private Service shiftPanel;
	
	private Service panelJava;
	private Service panelClass;
	private Service panelGif;
	private Service panelImage;
	private Service panelDefault;
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.a.swing.panel.shiftpanel");
	
		panelJava = Outside.service(this,"*gus.b.appview1.entryview.panel.java");
		panelClass = Outside.service(this,"*gus.b.appview1.entryview.panel.class1");
		panelGif = Outside.service(this,"*gus.b.appview1.entryview.panel.gif");
		panelImage = Outside.service(this,"*gus.b.appview1.entryview.panel.image");
		panelDefault = Outside.service(this,"*gus.b.appview1.entryview.panel.default1");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key==null) {resetGui();return;}
		
		Service s = findService(key);
		s.p(obj);
		shiftPanel.p(s);
	}
	
	
	private void resetGui() throws Exception
	{shiftPanel.p(null);}
	
	
	private Service findService(String key)
	{
		if(key.equals("java")) return panelJava;
		if(key.equals("class")) return panelClass;
		
		if(key.equals("gif")) return panelGif;
		if(key.equals("png")) return panelGif;
		
		if(key.equals("jpg")) return panelImage;
		if(key.equals("jpeg")) return panelImage;
		if(key.equals("bmp")) return panelImage;
		
		return panelDefault;
	}
}