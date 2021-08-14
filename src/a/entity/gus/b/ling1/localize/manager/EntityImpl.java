package a.entity.gus.b.ling1.localize.manager;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


public class EntityImpl implements Entity, V, G, ActionListener {

	public String creationDate() {return "20210814";}


	private Service builder;
	private Service langManager;
	
	private Set set;
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus.b.ling1.localize.builder");
		langManager = Outside.service(this,"gus.b.ling1.language.manager");
		
		set = new HashSet();
		langManager.addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return set;}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		Object holder = builder.t(new Object[] {key, obj});
		((E) holder).e();
		set.add(holder);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{languageChanged();}
	
	
	
	private void languageChanged()
	{
		try
		{
			Iterator it = set.iterator();
			while(it.hasNext()) execute((E) it.next());
		}
		catch(Exception e)
		{Outside.err(this,"languageChanged()",e);}
	}
	
	
	private void execute(E e)
	{
		try{e.e();}
		catch(Exception ex)
		{Outside.err(this,"execute(E)",ex);}
	}
}
