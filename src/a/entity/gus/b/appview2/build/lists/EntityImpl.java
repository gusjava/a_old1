package a.entity.gus.b.appview2.build.lists;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210822";}

	private Service buildEntries;

	public EntityImpl() throws Exception {
		buildEntries = Outside.service(this,"gus.b.appview1.build.entries");
	}
	
	
	public Object t(Object obj) throws Exception {
		File location = (File) obj;
		
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		List list4 = new ArrayList();

		List entries = (List) buildEntries.t(location);
		int nb = entries.size();
		for(int i=0;i<nb; i++) {
			String entry = formatEntry((String) entries.get(i));
			
			if(entry.startsWith("a/framework/")) 
				list1.add(entry);
			else if(entry.startsWith("a/core/")) 
				list2.add(entry);
			else if(entry.startsWith("a/entity/")) 
				list3.add(entry);
			else if(entry.startsWith("a/config/")) 
				list4.add(entry);
		}
		
		return new List[] {list1, list2, list3, list4};
	}
	
	
	private String formatEntry(String entry) {
		return entry.replace("\\", "/");
	}
}
