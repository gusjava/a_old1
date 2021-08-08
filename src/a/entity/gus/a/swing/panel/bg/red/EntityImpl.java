package a.entity.gus.a.swing.panel.bg.red;

import java.awt.Color;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	
	public String creationDate() {
		return "20210805";
	}
	
	private JPanel panel;
	
	public EntityImpl() {
		panel = new JPanel();
		panel.setBackground(Color.RED);
	}

	public Object i() throws Exception {
		return panel;
	}
}
