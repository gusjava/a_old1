package a.core.gus.gyem.m050.g.err.list;

import java.util.ArrayList;
import java.util.List;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private List list = new ArrayList();

	public Object g() throws Exception {
		return list;
	}
}
