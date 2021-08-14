package a.entity.gus.c.devtool1.init.menu;

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
	
	public EntityImpl() throws Exception {
		getMenuBar = Outside.service(this,"gus.b.appli1.gui.menubar");
		getMenuLang = Outside.service(this,"gus.b.ling1.language.menu");
	}

	public void e() throws Exception {
		JMenu menuLang = (JMenu) getMenuLang.i();
		JMenuBar bar = (JMenuBar) getMenuBar.g();
		bar.add(menuLang);
	}
}
