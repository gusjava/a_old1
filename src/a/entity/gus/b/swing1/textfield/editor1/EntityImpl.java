package a.entity.gus.b.swing1.textfield.editor1;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.S;
import a.framework.S1;
import a.framework.Service; 


public class EntityImpl extends S1 implements Entity, I, G, P, ActionListener {

	public String creationDate() {return "20210818";}


	private Service textChanged;
	private Service actionClear;

	private JTextField field;
	private Object sup;

	public EntityImpl() throws Exception
	{
		textChanged = Outside.service(this,"gus.b.swing1.textcomp.build.delayholder");
		actionClear = Outside.service(this,"gus.a.swing.textcomp.cust.action.escap.clear");

		field = new JTextField();
		field.setMargin(new Insets(3,3,3,3));

		actionClear.p(field);

		sup = textChanged.t(field);
		((S) sup).addActionListener(this);
	}
	
	
	public Object g() throws Exception
	{return field.getText();}
	
	
	public Object i() throws Exception
	{return field;}
	
	
	public void p(Object obj) throws Exception
	{
		((P)sup).p("deactivate");
		field.setText(obj==null?"":(String) obj);
		((P)sup).p("activate");
	}


	public void actionPerformed(ActionEvent e)
	{changed();}


	private void changed()
	{send(this,"changed()");}
}
