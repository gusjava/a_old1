package a.entity.gus.b.appview1.entryview.panel.default1;

import java.io.InputStream;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210813";}


	private Service view;
	private Service isToString;

	public EntityImpl() throws Exception {
		view = Outside.service(this,"*gus.b.dataview1.string");
		isToString = Outside.service(this,"gus.a.io.transfer.tostring");
	}
	
	
	public Object i() throws Exception {
		return view.i();
	}
	
	
	public void p(Object obj) throws Exception {
		InputStream is = (InputStream) obj;
		String src = (String) isToString.t(is);
		view.p(src);
	}
}
