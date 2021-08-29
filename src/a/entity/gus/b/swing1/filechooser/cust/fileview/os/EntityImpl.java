package a.entity.gus.b.swing1.filechooser.cust.fileview.os;

import a.framework.*;

import java.io.File;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileView;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210829";}


	private Service fileIcon;

	public EntityImpl() throws Exception
	{
		fileIcon = Outside.service(this,"gus.a.file.icon.os");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JFileChooser fc = (JFileChooser) obj;
		fc.setFileView(new FileView0());
	}
	
	
	
	private class FileView0 extends FileView
	{	
		public String getName(File f) {return null;}
		public String getDescription(File f) {return null;}
		public Boolean isTraversable(File f) {return null;}
		public Icon getIcon(File f) {return findIcon(f);}
	}
	
	
	
	private Icon findIcon(File f)
	{
		try{return (Icon) fileIcon.t(f);}
		catch(Exception e){Outside.err(this,"findIcon(File)",e);}
		return null;
	}
}
