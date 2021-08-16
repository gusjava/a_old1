package a.entity.gus.b.ling1.language.menu;

import java.util.List;

import a.framework.*;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20210814";}

	public static final String KEY1 = "m_lang_language";
	public static final String KEY2 = "m_lang_";


	private Service langManager;
	private Service langList;
	private Service localize;
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		langManager = Outside.service(this,"gus.b.ling1.language.manager");
		langList = Outside.service(this,"gus.b.ling1.language.list");
		localize = Outside.service(this,"gus.b.ling1.localize.manager");
		
		List l = (List) langList.g();
		String lang0 = (String) langManager.g();
		
		menu = new JMenu("Language");
		localize.v(KEY1,menu);
		
		ButtonGroup group = new ButtonGroup();
		
		for(int i=0;i<l.size();i++)
		{
			String lang = (String) l.get(i);
			boolean selected = lang.equals(lang0);
			
			JRadioButtonMenuItem1 item = new JRadioButtonMenuItem1(lang,selected);
			localize.v(KEY2+lang,item);
			
			group.add(item);
			menu.add(item);
		}
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	
	private void changeLang(String id)
	{
		try{langManager.p(id);}
		catch(Exception e)
		{Outside.err(this,"changeLang(String)",e);}
	}
	
	
	
	
	
	private class JRadioButtonMenuItem1 extends JRadioButtonMenuItem implements ChangeListener
	{
		private String lang;
		public JRadioButtonMenuItem1(String lang, boolean selected)
		{
			super(lang,selected);
			this.lang = lang;
			addChangeListener(this);
		}
		
		public void stateChanged(ChangeEvent e)
		{if(isSelected()) changeLang(lang);}
	}
}
