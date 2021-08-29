package a.entity.gus.b.dataview1.map.main.loadedentity;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.S;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210811";}

	private Service watcher;
	private Service viewer;
	private Service renderer;
	private Map map;
	private Icon icon;
	
	private JButton buttonClear;
	private JPanel panel;
	private JList list;
	

	
	public EntityImpl() throws Exception {
		watcher = Outside.service(this,"gus.b.gyem1.watcher");
		viewer = Outside.service(this,"*gus.b.dataview1.map");
		renderer = Outside.service(this,"gus.a.swing.list.cust.renderer.icon");
		map = (Map) Outside.resource(this,"g#m018.t.entity.findclass");
		icon = (Icon) Outside.resource(this,"icon#ELEMENT_entity_entityloaded");
		
		S watcherEntityUnique = (S) watcher.r("supportEntityLoaded");
		watcherEntityUnique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {refresh();}}
		);
		
		buttonClear = new JButton("Clear");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {clear();}}
		);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) viewer.i(), BorderLayout.CENTER);
		panel.add(buttonClear, BorderLayout.SOUTH);
		
		list = (JList) viewer.r("list");
		renderer.p(new Object[] {list, icon});
		
		viewer.p(map);
	}
	
	public Object i() throws Exception {
		return panel;
	}

	
	private void clear() {
		try {
			map.clear();
			viewer.p(map);
		}
		catch(Exception e) {
			Outside.err(this,"clear()",e);
		}
	}
	
	private void refresh() {
		try {
			viewer.e();
		}
		catch(Exception e) {
			Outside.err(this,"refresh()",e);
		}
	}
}
