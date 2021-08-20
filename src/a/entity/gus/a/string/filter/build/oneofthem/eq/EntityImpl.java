package a.entity.gus.a.string.filter.build.oneofthem.eq;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210819";}

	
	
	public Object t(Object obj) throws Exception
	{return new Filter((String) obj);}
	
	
	private class Filter implements F
	{
		private String[] n;
		public Filter(String s)
		{
			if(s==null || s.equals("")) return;
			n = s.split(" +");
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(n==null) return true;
			
			String s = (String) obj;
			for(int i=0;i<n.length;i++)
				if(s.equals(n[i])) return true;
			return false;
		}
	}
}
