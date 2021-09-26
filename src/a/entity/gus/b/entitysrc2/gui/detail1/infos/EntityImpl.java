package a.entity.gus.b.entitysrc2.gui.detail1.infos;


import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210907";}


	private Service tabHolder;
	private Service gui1;
	private Service gui2;
	private Service gui3;

	private Object holder;
	
	
	public EntityImpl() throws Exception
	{
		tabHolder = Outside.service(this,"*gus.b.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.infos.calls");
		gui2 = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.infos.downlinks");
		gui3 = Outside.service(this,"*gus.b.entitysrc2.gui.detail1.infos.uplinks");

		tabHolder.v("Calls", gui1);
		tabHolder.v("Down links", gui2);
		tabHolder.v("Up links", gui3);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		holder = obj;
		
		gui1.p(holder);
		gui2.p(holder);
		gui3.p(holder);
	}
	
	private void reset() throws Exception
	{
		holder = null;
		gui1.p(null);
		gui2.p(null);
		gui3.p(null);
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
}
