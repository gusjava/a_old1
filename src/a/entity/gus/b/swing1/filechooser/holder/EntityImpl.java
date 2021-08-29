package a.entity.gus.b.swing1.filechooser.holder;

import a.framework.*;
import javax.swing.JFileChooser;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210829";}

	private Service custFileView;
	private Service persister;
	
	private JFileChooser fc;
	
	public EntityImpl() throws Exception
	{
		custFileView = Outside.service(this,"gus.b.swing1.filechooser.cust.fileview.os");
		persister = Outside.service(this,"gus.b.persist1.swing.filechooser.directory");
		
		fc = new JFileChooser();
		custFileView.p(fc);
		persister.v(getClass().getName()+"_fc",fc);
	}
	
	
	public Object i() throws Exception
	{return fc;}
}
