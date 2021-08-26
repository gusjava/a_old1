package a.entity.gus.b.dataview1.exception;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.*;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210826";}

	
	private Service exceptionToDesc;
	
	private JTextArea area;
	private JScrollPane scroll;
	
	private Exception data;

	
	public EntityImpl() throws Exception {
		exceptionToDesc = Outside.service(this,"gus.a.throwable.build.description");
		area = new JTextArea();
		
		area.setEditable(false);
		area.setForeground(Color.RED);
		area.setBackground(Color.BLACK);
		area.setFont(area.getFont().deriveFont((float) 14));
		
		scroll = new JScrollPane(area);
	}
	
	
	public Object g() throws Exception {
		return data;
	}
	
	
	public void p(Object obj) throws Exception {
		data = (Exception) obj;
		if(data==null) {area.setText("");return;}
		
		String s = (String) exceptionToDesc.t(data);
		area.setText(s);
		area.setCaretPosition(0);
	}
	
	
	public Object i() throws Exception {
		return scroll;
	}
}
