package a.entity.gus.b.swing1.list.build.fromicon;

import javax.swing.Icon;
import javax.swing.JList;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210817";}

	
	private Service renderer;
	private Service iconProvider;

	public EntityImpl() throws Exception {
		renderer = Outside.service(this,"gus.a.swing.list.cust.renderer.icon");
		iconProvider = Outside.service(this,"gus.b.icons1.provider");
	}
	
	
	public Object t(Object obj) throws Exception {
		Icon icon = toIcon(obj);
		JList list = new JList();
		renderer.p(new Object[] {list,icon});
		return list;
	}
	
	private Icon toIcon(Object obj) throws Exception {
		if(obj instanceof Icon) return (Icon) obj;
		if(obj instanceof String) return (Icon) iconProvider.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
