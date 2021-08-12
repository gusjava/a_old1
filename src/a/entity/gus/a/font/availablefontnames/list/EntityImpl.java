package a.entity.gus.a.font.availablefontnames.list;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import a.framework.Entity;
import a.framework.G;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210812";}


	private List list;

	public EntityImpl() throws Exception
	{
		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		list = new ArrayList();
		for(String name:names)
		list.add(name);
	}
	
	public Object g() throws Exception
	{return list;}
}