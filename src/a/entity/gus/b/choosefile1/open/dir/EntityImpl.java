package a.entity.gus.b.choosefile1.open.dir;

import a.framework.*;
import javax.swing.JFileChooser;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210829";}

	private Service fcHolder;
	
	public EntityImpl() throws Exception
	{
		fcHolder = Outside.service(this,"gus.b.swing1.filechooser.holder");
	}
	
	public Object g() throws Exception
	{
		JFileChooser fc = (JFileChooser) fcHolder.i();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int val = fc.showOpenDialog(null);
		if(val==JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile();
		return null;
	}
}
