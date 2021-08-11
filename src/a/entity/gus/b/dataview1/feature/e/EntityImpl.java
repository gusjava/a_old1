package a.entity.gus.b.dataview1.feature.e;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import a.framework.E;
import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;

public class EntityImpl implements Entity, G, P, I, ActionListener {
	public String creationDate() {return "20210811";}


	private JPanel panel;
	private JButton button;
	
	private E data;
	
	
	public EntityImpl() throws Exception
	{
		button = new JButton("Call e()");
		button.setEnabled(false);
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (E) obj;
		button.setEnabled(data!=null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{executeData();}
	
	
	private void executeData()
	{
		try{data.e();}
		catch(Exception e)
		{Outside.err(this,"executeData()",e);}
	}
}