package a.entity.gus.c.appli1.bottombar;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210826";}

	
	private Service iconsPanel;
	private Service devLabel;
	
	
	private JPanel panel;

	public EntityImpl() throws Exception {
		iconsPanel = Outside.service(this,"*gus.b.gyem1.watcher.icons");
		devLabel = Outside.service(this,"*gus.c.appli1.bottombar.devlabel");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) devLabel.i(), BorderLayout.WEST);
		panel.add((JComponent) iconsPanel.i(), BorderLayout.EAST);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
}
