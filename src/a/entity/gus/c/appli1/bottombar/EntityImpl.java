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

	
	private Service errorLabel;
	
	private JPanel panel;

	public EntityImpl() throws Exception {
		errorLabel = Outside.service(this,"*gus.b.errors1.watcher.icon");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) errorLabel.i(), BorderLayout.EAST);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
}
