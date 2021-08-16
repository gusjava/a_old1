package a.entity.gus.b.appli1.execute.about;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20210816";}


	private Service dialogPopup;
	private Service aboutPanel;
	


	public EntityImpl() throws Exception
	{
		dialogPopup = Outside.service(this,"gus.b.swing1.dialog.popup1");
		aboutPanel = Outside.service(this,"*gus.b.appli1.execute.about.panel");
	}
	
	
	public void e() throws Exception
	{dialogPopup.p(aboutPanel.i());}
}
