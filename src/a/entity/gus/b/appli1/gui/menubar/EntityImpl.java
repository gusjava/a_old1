package a.entity.gus.b.appli1.gui.menubar;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import a.framework.*;

public class EntityImpl implements Entity, G {
	
	public String creationDate() {
		return "20210806";
	}
	
	private Service mainFrameProvider;
	private JMenuBar menuBar;
	
	public EntityImpl() throws Exception {
		mainFrameProvider = Outside.service(this,"m024.g.mainframe.find");
		
		menuBar = new JMenuBar();
		
		JFrame mainFrame = (JFrame) mainFrameProvider.g();
		mainFrame.setJMenuBar(menuBar);
	}
	
	public Object g() throws Exception {
		return menuBar;
	}
}
