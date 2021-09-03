package a.entity.gus.b.persist1.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, R, V, ActionListener {
	public String creationDate() {return "20210814";}


	public static final long LAPSE = 2000;
	
	private Service persister1;
	private Service timer;
	private Service exit;

	private Map map;
	

	public EntityImpl() throws Exception
	{
		persister1 = Outside.service(this,"gus.b.persist1.main");
		timer = Outside.service(this,"gus.b.timer1.register");
		exit = Outside.service(this,"gus.b.cust1.exit");
		
		map = new HashMap();
		timer.v(""+LAPSE, (E) this::save);
		
		exit.addActionListener(this);
	}
	
	
	
	public Object r(String key) throws Exception
	{return persister1.r(key);}
	
	
	public void v(String key, Object obj) throws Exception
	{add(key,(G) obj);}


	public void actionPerformed(ActionEvent e)
	{save();}
	
	
	private synchronized void add(String key, G g) throws Exception
	{
		map.put(key,g);
		persister1.v(key,g.g());
	}
	
	
	
	private synchronized void save()
	{
		try
		{
			Iterator it = map.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				G g = (G) map.get(key);
				persister1.v(key,g.g());
			}
		}
		catch(Exception e)
		{Outside.err(this,"save()",e);}
	}
}