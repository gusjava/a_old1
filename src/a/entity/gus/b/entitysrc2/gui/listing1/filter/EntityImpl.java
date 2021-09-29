package a.entity.gus.b.entitysrc2.gui.listing1.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);

		List list = (List) o[0];
		String search = (String) o[1];
		String devId = (String) o[2];
		Set lockSet = (Set) o[3];
		
		F filter = new Filter(search, devId, lockSet);
		return filterList.t(new Object[]{list, filter});
	}
	
	
	private class Filter implements F {
		private String devId;
		private Set lockSet;
		
		private boolean all = false;
		private boolean onlyMine = false;
		private boolean ffStrict = false;
		
		private List ff = new ArrayList();
		private List st = new ArrayList();
		private List en = new ArrayList();
		private List co = new ArrayList();
		private List in = new ArrayList();
		
		public Filter(String search, String devId, Set lockSet) {
			if(search==null || search.trim().equals("")) {
				all = true;
				return;
			}
			this.devId = devId;
			this.lockSet = lockSet;
			
			if(search.contains("&")) {
				onlyMine = true;
				search = search.replace("&"," ");
			}
			
			if(search.contains("!")) {
				ffStrict = true;
				search = search.replace("!"," ");
			}
			
			String[] nn = search.split(" +");
			for(String n : nn) {
				String n0 = n.toLowerCase();
				
				if(n.matches("[BEFGHIPRSTV]+")) {
					for(int i=0;i<n0.length();i++) {
						ff.add(""+n0.charAt(i));
					}
				}
				else {
					if(n0.startsWith("*") && n0.endsWith("*")) {
						in.add(n0.substring(1, n0.length()-1));
					}
					else if(n0.startsWith("*")) {
						en.add(n0.substring(1));
					}
					else if(n0.endsWith("*")) {
						st.add(n0.substring(0, n0.length()-1));
					}
					else {
						co.add(n0);
					}
				}
			}
		}

		public boolean f(Object obj) throws Exception {
			if(all) return true;
			
			String[] data = (String[]) obj;
			String name = data[0];
			String features = data[1];
			
			if(lockSet.contains(name)) return true;
			
			if(onlyMine && !name.startsWith(devId+".")) return false;
			
			for(int i=0;i<ff.size();i++) {
				String f = (String) ff.get(i);
				if(!features.contains(f)) return false;
			}
			
			if(ffStrict) {
				for(int i=0;i<features.length();i++) {
					String feature = ""+features.charAt(i);
					if(!ff.contains(feature)) return false;
				}
			}

			int nbIn = in.size();
			int nbCo = co.size();
			int nbSt = st.size();
			int nbEn = en.size();
			
			if(nbIn + nbCo + nbSt + nbEn == 0) return true;
			
			for(int i=0;i<nbIn;i++) {
				String s = (String) in.get(i);
				if(name.matches(".+"+Pattern.quote(s)+".+")) return true;
			}
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
