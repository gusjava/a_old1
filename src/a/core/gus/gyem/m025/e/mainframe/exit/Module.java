package a.core.gus.gyem.m025.e.mainframe.exit;

import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.utils.UtilLog;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		UtilLog.println("Exiting application...");
		System.exit(0);
	}
}
