package a.entity.gus.b.errors1.watcher.icon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20210826";}

	
	private Service watcher;
	private List errList;
	private Icon icon;
	
	private JLabel label;
	

	public EntityImpl() throws Exception {
		watcher = Outside.service(this,"gus.b.errors1.watcher");
		errList = (List) Outside.resource(this,"errlist");
		icon = (Icon) Outside.resource(this,"icon#UTIL_error");
		
		label = new JLabel(" ");
		watcher.addActionListener(this);
	}
	
	
	public Object i() throws Exception {
		return label;
	}


	public void actionPerformed(ActionEvent e) {
		refreshGui();
	}
	
	private void refreshGui() {
		try {
			label.setIcon(icon);
			label.setText(errList.size()+" ");
		}
		catch(Exception e) {
			Outside.err(this,"refreshGui()",e);
		}
	}
}
