package a.entity.gus.b.servicecaller1.maingui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20210825";}
	
	public static final String SERVICE = "service";
	public static final String RESOURCE = "resource";

	private Service viewer;
	private Service actionClear;
	private Service comboWhite;
	
	private JPanel panel;
	private JLabel label;
	private JTextField field;
	private JComboBox combo;

	public EntityImpl() throws Exception {
		viewer = Outside.service(this,"*gus.b.dataview1.object");
		actionClear = Outside.service(this,"gus.a.swing.textcomp.cust.action.escap.clear");
		comboWhite = Outside.service(this,"gus.a.swing.combobox.cust.white");
		
		field = new JTextField();
		field.setMargin(new Insets(3,3,3,3));
		field.addActionListener(this);
		actionClear.p(field);
		
		combo = new JComboBox<>(new String[] {SERVICE, RESOURCE});
		comboWhite.p(combo);

		label = new JLabel(" ");
		
		JPanel panelInput = new JPanel(new BorderLayout());
		panelInput.add(combo, BorderLayout.WEST);
		panelInput.add(field, BorderLayout.CENTER);
		
		
		JPanel panelView = new JPanel(new BorderLayout());
		panelView.add(label, BorderLayout.NORTH);
		panelView.add((JComponent) viewer.i(), BorderLayout.CENTER);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panelInput, BorderLayout.NORTH);
		panel.add(panelView, BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}


	public void actionPerformed(ActionEvent e) {
		perform();
	}
	
	private void perform() {
		try {
			String input = field.getText();
			String type = (String) combo.getSelectedItem();
			label.setText(input);
			
			Object result = type.equals(SERVICE) ? 
				Outside.service(this, input) : 
				Outside.resource(this, input);
			
			viewer.p(result);
		}
		catch(Exception e) {
			Outside.err(this,"perform()",e);
		}
	}
}
