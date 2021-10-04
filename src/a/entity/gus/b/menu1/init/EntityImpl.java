package a.entity.gus.b.menu1.init;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20210806";}
	
	private Service getMenuBar;
	private Service entityGeneratorAsk;
	private Service rootDirOpen;
	private Service restartApp;
	
	public EntityImpl() throws Exception
	{
		getMenuBar = Outside.service(this,"gus.b.appli1.gui.menubar");
		entityGeneratorAsk = Outside.service(this,"gus.b.entitysrc1.generator1.ask");
		rootDirOpen = Outside.service(this,"gus.b.paths1.rootdir.open");
		restartApp = Outside.service(this,"gus.b.apprestart1.perform");
	}

	public void e() throws Exception
	{
		JMenuItem item1 = buildItem("Generate entity", entityGeneratorAsk);
		JMenuItem item2 = buildItem("Open root dir", rootDirOpen);
		JMenuItem item3 = buildItem("Restart", restartApp);
		
		JMenu menu1 = new JMenu("Menu1");
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		
		JMenuBar bar = (JMenuBar) getMenuBar.g();
		bar.add(menu1);
	}
	
	
	
	
	private JMenuItem buildItem(String text, E exe)
	{
		JMenuItem item = new JMenuItem(text);
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{execute(exe);}
		});
		return item;
	}
	
	
	private void execute(E exe)
	{
		try {exe.e();}
		catch (Exception e)
		{Outside.err(this,"execute(E)", e);}
	}
}
