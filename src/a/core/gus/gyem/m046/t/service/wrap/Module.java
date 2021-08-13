package a.core.gus.gyem.m046.t.service.wrap;

import java.awt.event.ActionListener;
import java.util.List;

import a.core.gus.gyem.GyemSystem;
import a.framework.B;
import a.framework.E;
import a.framework.F;
import a.framework.G;
import a.framework.H;
import a.framework.I;
import a.framework.P;
import a.framework.R;
import a.framework.S;
import a.framework.Service;
import a.framework.T;
import a.framework.V;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		Object[] data = (Object[]) obj;
		
		Object call = data[0];
		Object target = data[1];
		
		if(target==null) return moduleG(M014_G_SERVICE_BUILD_EMPTY).g();
		
		String callDesc = (String) moduleT(M047_T_SERVICE_CALL_DESCRIPTION).t(call);
		String targetName = target.getClass().getName();
		
		return new ServiceImpl(target, callDesc, targetName);
	}
	
	
	private class ServiceImpl implements Service {
		
		private String callDesc;
		private String targetName;
		
		private E e;
	    private P p;
	    private G g;
	    private T t;
	    private F f;
	    private B b;
	    private H h;
	    private I i;
	    private V v;
	    private R r;
	    private S s;
		
		private ServiceImpl(Object target, String callDesc, String targetName) {
			this.callDesc = callDesc;
			this.targetName = targetName;
			
			if(target instanceof E)			e = (E) target;
	        if(target instanceof P)			p = (P) target;
	        if(target instanceof G)			g = (G) target;
	        if(target instanceof T)			t = (T) target;
	        if(target instanceof F)			f = (F) target;
	        if(target instanceof B)			b = (B) target;
	        if(target instanceof H)			h = (H) target;
	        if(target instanceof I)			i = (I) target;
	        if(target instanceof V)			v = (V) target;
	        if(target instanceof R)			r = (R) target;
	        if(target instanceof S)			s = (S) target;
		}
		
		private void check(Object obj, Class c) throws Exception {
			if(obj == null) throw new Exception("Feature " + c.getSimpleName() + " not available for " + callDesc + " (=" +targetName + ")");
		}

		public void e() throws Exception {
			 check(e, E.class); 
			 e.e();
		}

		public void p(Object obj) throws Exception {
			 check(p, P.class); 
			 p.p(obj);
		}

		public Object g() throws Exception {
			 check(g, G.class); 
			return g.g();
		}

		public Object t(Object obj) throws Exception {
			 check(t, T.class); 
			return t.t(obj);
		}

		public boolean f(Object obj) throws Exception {
			 check(f, F.class); 
			return f.f(obj);
		}

		public boolean b() throws Exception {
			 check(b, B.class); 
			return b.b();
		}

		public double h(double value) throws Exception {
			 check(h, H.class); 
			return h.h(value);
		}

		public Object i() throws Exception {
			 check(i, I.class); 
			return i.i();
		}

		public void v(String key, Object obj) throws Exception {
			 check(v, V.class); 
			v.v(key, obj);
		}
		
		public Object r(String key) throws Exception {
			 check(r, R.class); 
			return r.r(key);
		}

		public void addActionListener(ActionListener listener) throws Exception {
			 check(s, S.class); 
			 s.addActionListener(listener);
		}

		public void removeActionListener(ActionListener listener) throws Exception {
			 check(s, S.class); 
			 s.removeActionListener(listener);
		}

		public List listeners() throws Exception {
			 check(s, S.class); 
			return s.listeners();
		}
	}
}
