package a.entity.gus.b.menu1.init;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, E {
	
	public String creationDate() {
		return "20210806";
	}
	
	private Service getMenuBar;
	private Service entityGenerator;
	
	public EntityImpl() throws Exception {
		getMenuBar = Outside.service(this,"gus.a.appli.gui.menubar");
		entityGenerator = Outside.service(this,"gus.b.entitysrc1.generator1");
	}

	public void e() throws Exception {
		
		JMenuItem item1 = new JMenuItem("Generate entity");
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateEntity();
			}
		});
		
		JMenu menu1 = new JMenu("Menu1");
		menu1.add(item1);
		
		JMenuBar bar = (JMenuBar) getMenuBar.g();
		bar.add(menu1);
	}
	
	
	private void generateEntity() {
		try {
			System.out.println("generate entity");
			
			String rule = JOptionPane.showInputDialog("Generation rule");
			if(rule==null || rule.equals("")) return;
			
			entityGenerator.p(rule);
		} catch (Exception e) {
			Outside.err(this,"generateEntity()", e);
		}
	}
}
