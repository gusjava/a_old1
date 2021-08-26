package a.core.gus.gyem.m050.g.err.list;

import java.util.List;
import java.util.Vector;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private List list = new Vector();

	public Object g() throws Exception {
		return list;
	}
}
