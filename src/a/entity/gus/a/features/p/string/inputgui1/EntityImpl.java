package a.entity.gus.a.features.p.string.inputgui1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;

public class EntityImpl implements Entity, P, I, ActionListener {
	public String creationDate() {return "20210813";}

	
	private JPanel panel;
	private JTextArea area;
	private JButton buttonPerform;
	private JButton buttonClear;
	
	private P handler;
	

	public EntityImpl() throws Exception {
		area = new JTextArea();
		area.setMargin(new Insets(5, 5, 5, 5));
		
		buttonPerform = new JButton("Perform");
		buttonPerform.addActionListener(this);
		
		buttonClear = new JButton("Clear");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {area.setText("");}
		});
		
		JPanel panel1 = new JPanel(new GridLayout(1,2));
		panel1.add(buttonClear);
		panel1.add(buttonPerform);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		panel.add(panel1, BorderLayout.SOUTH);
		
		buttonPerform.setEnabled(false);
	}
	
	
	public void p(Object obj) throws Exception {
		handler = (P) obj;
		buttonPerform.setEnabled(handler!=null);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		perform();
	}
	
	
	private void perform() {
		try {
			String text = area.getText();
			if(handler!=null) {
				handler.p(text);
			}
		}
		catch(Exception e) {
			Outside.err(this,"perform()",e);
		}
	}
}
