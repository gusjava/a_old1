package a.entity.gus.b.appli1.gui.menubar;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import a.framework.*;

public class EntityImpl implements Entity, G {
	
	public String creationDate() {
		return "20210806";
	}
	
	private JFrame mainFrame;
	private JMenuBar menuBar;
	
	public EntityImpl() throws Exception {
		mainFrame = (JFrame) Outside.resource(this,"mainframe");
		
		menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);
	}
	
	public Object g() throws Exception {
		return menuBar;
	}
}
