package a.entity.gus.b.dataview1.entity;

import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210811";}

	private Service formPanel;
	private Service findName;
	private Service findFeatures;

	private JLabel labelName;
	private JLabel labelDate;
	private JLabel labelFeatures;

	private Entity data;
	
	

	public EntityImpl() throws Exception {
		formPanel = Outside.service(this,"*gus.a.swing.panel.formpanel");
		findFeatures = Outside.service(this,"gus.a.entity.objtofeatures");
		findName = Outside.service(this,"entityname");

		labelName = new JLabel(" ");
		labelDate = new JLabel(" ");
		labelFeatures = new JLabel(" ");
		
		formPanel.v("Entity name",labelName);
		formPanel.v("Creation date",labelDate);
		formPanel.v("Features",labelFeatures);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Entity) obj;
		if(data==null)
		{
			labelName.setText(" ");
			labelDate.setText(" ");
			labelFeatures.setText(" ");
		}
		else
		{
			labelName.setText(getName(data));
			labelDate.setText(data.creationDate());
			labelFeatures.setText(getFeatures(data));
		}
	}
	
			
	private String getName(Entity entity) throws Exception
	{return (String) findName.t(entity);}
	
	
	private String getFeatures(Entity entity) throws Exception
	{return (String) findFeatures.t(entity);}
}
