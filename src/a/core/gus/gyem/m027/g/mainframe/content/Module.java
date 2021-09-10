package a.core.gus.gyem.m027.g.mainframe.content;

import java.awt.Container;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private Container content;

	public Object g() throws Exception {
		if(content==null) init();
		return content;
	}
	
	private void init() throws Exception {
		try {
			content = (Container) moduleG(M028_G_BUILDGUI_ENTITY).g();
			if(content==null) content = (Container) moduleG(M029_G_BUILDGUI_DEFAULTPANEL).g();
		}
		catch(Exception e) {
			content = (Container) moduleT(M030_T_BUILDGUI_ERRORPANEL).t(e);
			e.printStackTrace();
		}
	}
}
