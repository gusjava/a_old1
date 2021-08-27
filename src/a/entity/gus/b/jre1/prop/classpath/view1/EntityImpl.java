package a.entity.gus.b.jre1.prop.classpath.view1;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210825";}

	
	private Service findClassPath;
	private Service viewer;

	public EntityImpl() throws Exception {
		findClassPath = Outside.service(this,"gus.a.jre.prop.classpath.as.filearray");
		viewer = Outside.service(this,"*gus.b.filearray1.view1");
		
		Object data = findClassPath.g();
		viewer.p(data);
	}
	
	public Object i() throws Exception {
		return viewer.i();
	}
}
