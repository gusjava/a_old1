package a.entity.gus.b.entitysrc1.listinggui1.filter;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210819";}

	private Service buildFilter;
	private Service filterList;

	public EntityImpl() throws Exception
	{
		buildFilter = Outside.service(this,"gus.a.string.filter.build.oneofthem.co_i");
		filterList = Outside.service(this,"gus.a.list.filter.keep");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		List list = (List) o[0];
		String search = (String) o[1];
		
		F filter = (F) buildFilter.t(search);
		return filterList.t(new Object[]{list, filter});
	}
}
