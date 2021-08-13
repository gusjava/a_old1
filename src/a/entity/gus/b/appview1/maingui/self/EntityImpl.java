package a.entity.gus.b.appview1.maingui.self;

import java.io.File;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210813";}

	
	private Service mainGui;
	private Service appLocation;

	public EntityImpl() throws Exception {
		mainGui = Outside.service(this,"*gus.b.appview1.maingui");
		appLocation = Outside.service(this,"gus.a.app.location");
		
		File location = (File) appLocation.g();
		mainGui.p(location);
	}
	
	public Object i() throws Exception {
		return mainGui.i();
	}
}
