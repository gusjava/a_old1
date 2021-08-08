package a.core.gus.gyem.m014.g.service.build.empty;

import java.awt.event.ActionListener;
import java.util.List;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;
import a.framework.Service;

public class Module extends GyemSystem implements G {
	
	public Object g() throws Exception {
		return new EmptyService();
	}
	
	
	private class EmptyService implements Service {

		public void e() throws Exception {}
		public Object g() throws Exception {return null;}
		public Object t(Object obj) throws Exception {return null;}
		public void p(Object obj) throws Exception {}
		public boolean f(Object obj) throws Exception {return false;}
		public boolean b() throws Exception {return false;}
		public double h(double value) throws Exception {return 0;}
		public Object i() throws Exception {return null;}
		public Object r(String key) throws Exception {return null;}
		public void addActionListener(ActionListener listener) throws Exception {}
		public void removeActionListener(ActionListener listener) throws Exception {}
		public List listeners() throws Exception {return null;}
		public void v(String key, Object obj) throws Exception {}
	}
}
