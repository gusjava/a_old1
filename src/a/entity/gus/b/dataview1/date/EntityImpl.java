package a.entity.gus.b.dataview1.date;

import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.P;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210811";}


	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

	private Date data;

	private JTextArea area;
	private JScrollPane scroll;

	public EntityImpl() throws Exception {
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		scroll = new JScrollPane(area);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	public void p(Object obj) throws Exception {
		data = (Date) obj;
		area.setText(display());
	}
	
	
	private String display() {
		if(data==null) return "";
		return data+"\n"+sdf.format(data)+"\ntime: "+data.getTime();
	}
}