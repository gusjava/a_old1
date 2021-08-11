package a.entity.gus.a.awt.beep;

import java.awt.Toolkit;

import a.framework.E;
import a.framework.Entity;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20210811";}
	
	
	public void e() throws Exception {
		Toolkit.getDefaultToolkit().beep();
	}
}
