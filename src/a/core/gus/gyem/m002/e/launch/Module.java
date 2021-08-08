package a.core.gus.gyem.m002.e.launch;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;

public class Module extends GyemSystem implements E {

	public void e() throws Exception {
		moduleP(M021_P_EXECUTE_SEQUENCE).p(PROP_BEFORE);
		moduleE(M023_E_MAINFRAME).e();
		moduleP(M021_P_EXECUTE_SEQUENCE).p(PROP_AFTER);
	}
}
