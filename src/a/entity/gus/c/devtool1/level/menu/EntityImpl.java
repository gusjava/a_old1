package a.entity.gus.c.devtool1.level.menu;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20210815";}
	
	public static final String LEVEL1 = "level1";
	public static final String LEVEL2 = "level2";
	public static final String LEVEL3 = "level3";


	public static final String KEY1 = "m_main_level";
	public static final String KEY2 = "m_main_";


	private Service levelManager;
	private Service localize;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		levelManager = Outside.service(this,"gus.c.devtool1.level.manager");
		localize = Outside.service(this,"gus.b.ling1.localize.manager");
		
		String level0 = (String) levelManager.g();
		
		menu = new JMenu("Level");
		localize.v(KEY1, menu);
		
		ButtonGroup group = new ButtonGroup();
		
		for(String level : new String[] {LEVEL1, LEVEL2, LEVEL3})
		{
			boolean selected = level.equals(level0);
			JRadioButtonMenuItem1 item = new JRadioButtonMenuItem1(level, selected);
			localize.v(KEY2+level,item);
			
			group.add(item);
			menu.add(item);
		}
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	
	private void changeLang(String id)
	{
		try{levelManager.p(id);}
		catch(Exception e)
		{Outside.err(this,"changeLang(String)",e);}
	}
	
	
	
	
	
	private class JRadioButtonMenuItem1 extends JRadioButtonMenuItem implements ChangeListener
	{
		private String level;
		public JRadioButtonMenuItem1(String level, boolean selected)
		{
			super(level, selected);
			this.level = level;
			addChangeListener(this);
		}
		
		public void stateChanged(ChangeEvent e)
		{if(isSelected()) changeLang(level);}
	}
}
