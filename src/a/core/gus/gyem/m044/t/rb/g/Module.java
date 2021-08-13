package a.core.gus.gyem.m044.t.rb.g;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		G g = (G) moduleT(M040_T_RESOURCE_PROVIDE).t(obj);
		if(g==null) throw new Exception("Enable to call g on null");
		return g.g();
	}
}
