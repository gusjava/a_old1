package a.entity.gus.a.set.filter.keep;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import a.framework.Entity;
import a.framework.F;
import a.framework.P;
import a.framework.T;

public class EntityImpl implements Entity, T, P {
	public String creationDate() {return "20210819";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		F filter = (F) o[1];
		
		Set output = new HashSet();
		
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(filter.f(element)) output.add(element);
		}
		return output;
	}


	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		F filter = (F) o[1];
		
		Iterator it = input.iterator();
		while(it.hasNext())
		{
			Object element = it.next();
			if(!filter.f(element)) it.remove();
		}
	}
}
