package a.entity.gus.b.dataview1.feature.g;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I, ActionListener {
	public String creationDate() {return "20210811";}


	private Service viewer;
	
	private JPanel panel;
	private JButton button;
	
	private G data;
	
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus.b.dataview1.object");
		
		button = new JButton("Call g()");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (G) obj;
		button.setEnabled(data!=null);
		viewer.p(null);
	}
	
	public void actionPerformed(ActionEvent e)
	{showData();}
	
	
	private void showData()
	{
		try{viewer.p(data.g());}
		catch(Exception e){Outside.err(this,"showData()",e);}
	}
}