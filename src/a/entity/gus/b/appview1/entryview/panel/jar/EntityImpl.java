package a.entity.gus.b.appview1.entryview.panel.jar;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.io.InputStream;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210821";}

	
	private Service buildEntries;
	private Service listToString;
	
	private JTextArea area;
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		buildEntries = Outside.service(this,"gus.a.io.jar.build.entries");
		listToString = Outside.service(this,"gus.a.tostring.list");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3, 3, 3, 3));
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		List list = (List) buildEntries.t(is);
		String s = (String) listToString.t(list);
		
		area.setText(s);
		area.setCaretPosition(0);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
