package a.entity.gus.b.entitysrc2.gui.listing1.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210901";}


	private Service filterList;

	public EntityImpl() throws Exception
	{
		filterList = Outside.service(this,"gus.a.list.filter.keep");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);

		List list = (List) o[0];
		String search = (String) o[1];
		Set lockSet = (Set) o[2];
		
		F filter = new Filter(search, lockSet);
		return filterList.t(new Object[]{list, filter});
	}
	
	
	private class Filter implements F {
		private Set lockSet;
		private boolean all = false;
		private List ff = new ArrayList();
		private List st = new ArrayList();
		private List en = new ArrayList();
		private List co = new ArrayList();
		
		public Filter(String search, Set lockSet) {
			if(search==null || search.equals("")) {
				all = true;
				return;
			}
			this.lockSet = lockSet;
			
			String[] nn = search.split(" +");
			for(String n : nn) {
				String n0 = n.toLowerCase();
				String n1 = n.toUpperCase();
				
				if(n1.equals(n)) {
					for(int i=0;i<n.length();i++) {
						ff.add(""+n0.charAt(i));
					}
				}
				else if(n.startsWith("*") && n.endsWith("*")) {
						co.add(n0.substring(1, n0.length()-1));
				}
				else if(n.startsWith("*")) {
					en.add(n0.substring(1));
				}
				else if(n.endsWith("*")) {
					st.add(n0.substring(0, n0.length()-1));
				}
				else {
					co.add(n0);
				}
			}
		}

		public boolean f(Object obj) throws Exception {
			if(all) return true;
			
			String[] data = (String[]) obj;
			String name = data[0];
			String features = data[1];
			
			if(lockSet.contains(name)) return true;
			
			for(int i=0;i<ff.size();i++) {
				String f = (String) ff.get(i);
				if(!features.contains(f)) return false;
			}
			
			int nbCo = co.size();
			int nbSt = st.size();
			int nbEn = en.size();
			
			if(nbCo + nbSt + nbEn == 0) return true;
			
			for(int i=0;i<nbCo;i++) {
				String s = (String) co.get(i);
				if(name.contains(s)) return true;
			}
			for(int i=0;i<nbSt;i++) {
				String s = (String) st.get(i);
				if(name.startsWith(s)) return true;
			}
			for(int i=0;i<nbEn;i++) {
				String s = (String) en.get(i);
				if(name.endsWith(s)) return true;
			}
			return false;
		}
	}
}