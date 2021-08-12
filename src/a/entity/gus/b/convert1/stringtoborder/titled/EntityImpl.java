package a.entity.gus.b.convert1.stringtoborder.titled;

import javax.swing.BorderFactory;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}


	public Object t(Object obj) throws Exception
	{
		String title = (String) obj;
		return BorderFactory.createTitledBorder(title);
	}
}