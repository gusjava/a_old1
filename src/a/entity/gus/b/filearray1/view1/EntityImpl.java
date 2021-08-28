package a.entity.gus.b.filearray1.view1;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, G, I {
	public String creationDate() {return "20210825";}

	private Service renderer;
	
	private JPanel panel;
	private JList list;
	private JLabel label;
	
	private File[] data;
	

	public EntityImpl() throws Exception {
		renderer = Outside.service(this,"gus.b.swing1.list.cust.renderer.file.path");
		
		list = new JList();
		renderer.p(list);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}
	
	
	public void p(Object obj) throws Exception {
		data = (File[]) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
	
	
	private void resetGui() {
		list.setListData(new File[0]);
		label.setText(" ");
	}
	
	private void updateGui() {
		list.setListData(data);
		label.setText(" "+data.length);
	}
}
