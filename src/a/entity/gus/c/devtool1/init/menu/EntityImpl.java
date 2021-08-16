package a.entity.gus.c.devtool1.init.menu;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20210814";}
	
	private Service getMenuBar;
	private Service getMenuLang;
	private Service getMenuLevel;
	private Service getActionAbout;
	private Service getActionExit;
	private Service localize;
	
	public EntityImpl() throws Exception {
		getMenuBar = Outside.service(this,"gus.b.appli1.gui.menubar");
		getMenuLang = Outside.service(this,"gus.b.ling1.language.menu");
		getMenuLevel = Outside.service(this,"gus.c.devtool1.level.menu");
		getActionAbout = Outside.service(this,"gus.b.appli1.action.about.ling");
		getActionExit = Outside.service(this,"gus.b.appli1.action.exit.ling");
		localize = Outside.service(this,"gus.b.ling1.localize.manager");
	}

	public void e() throws Exception {
		JMenu menu1 = new JMenu("Menu1");
		localize.v("m_main_menu1", menu1);
		
		menu1.add((JMenu) getMenuLang.i());
		menu1.add((JMenu) getMenuLevel.i());
		menu1.addSeparator();
		menu1.add((Action) getActionAbout.g());
		menu1.add((Action) getActionExit.g());
		
		JMenuBar bar = (JMenuBar) getMenuBar.g();
		bar.add(menu1);
	}
}
