package a.core.gus.gyem.m023.e.mainframe;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		Object frame = moduleG(M024_G_MAINFRAME_FIND).g();
		if(frame!=null) moduleP(M026_P_MAINFRAME_HANDLE).p(frame);
	}
}
