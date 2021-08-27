package a.entity.gus.b.dataview1.feature.r;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import a.framework.*;

public class EntityImpl implements Entity, G, P, I, ActionListener {
	public String creationDate() {return "20210811";}

	private Service viewer;
	private Service actionClear;

	private JPanel panel;
	private JLabel label;
	private JTextField field;
	
	private R data;

	
	public EntityImpl() throws Exception {
		viewer = Outside.service(this,"*gus.b.dataview1.object");
		actionClear = Outside.service(this,"gus.a.swing.textcomp.cust.action.escap.clear");
		
		field = new JTextField();
		field.setMargin(new Insets(3,3,3,3));
		field.addActionListener(this);
		actionClear.p(field);

		label = new JLabel(" ");

		JPanel panelView = new JPanel(new BorderLayout());
		panelView.add(label, BorderLayout.NORTH);
		panelView.add((JComponent) viewer.i(), BorderLayout.CENTER);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(panelView, BorderLayout.CENTER);
	}
	
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public void p(Object obj) throws Exception {
		data = (R) obj;
	}
	
	
	public Object i() throws Exception {
		return panel;
	}


	public void actionPerformed(ActionEvent e) {
		perform();
	}
	
	private void perform() {
		try {
			if(data==null) return;
			String input = field.getText();
			label.setText(input);
			
			Object result = data.r(input);
			viewer.p(result);
		}
		catch(Exception e) {
			Outside.err(this,"perform()",e);
		}
	}
}
