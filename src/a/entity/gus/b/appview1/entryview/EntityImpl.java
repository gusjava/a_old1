package a.entity.gus.b.appview1.entryview;

import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210813";}

	
	private Service handleInputStream;
	private Service entryViewPanel;
	private Service pathToIcon;
	
	private JPanel panel;
	private JLabel labelTitle;

	private File location;
	private String entry;
	
	
	public EntityImpl() throws Exception {
		handleInputStream = Outside.service(this,"gus.b.appview1.handle.entryinputstream");
		entryViewPanel = Outside.service(this,"*gus.b.appview1.entryview.panel");
		pathToIcon = Outside.service(this,"gus.b.files1.icon.name");
		
		labelTitle = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle, BorderLayout.NORTH);
		panel.add((JComponent) entryViewPanel.i(), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
	
	
	public void p(Object obj) throws Exception {
		if(obj==null) {
			location = null;
			entry = null;
			resetGui();
		}
		else {
			Object[] data = (Object[]) obj;
			location = (File) data[0];
			entry = (String) data[1];
			updateGui();
		}
	}
	
	
	
	private void resetGui() throws Exception {
		labelTitle.setText(" ");
		labelTitle.setIcon(null);
		entryViewPanel.v(null,null);
	}
	
	
	private void updateGui() throws Exception {
		labelTitle.setText(entry);
		labelTitle.setIcon(icon(entry));
		
		P handler = obj->handleInputStream((InputStream) obj);
		handleInputStream.p(new Object[] {location, entry, handler});
	}
	
	private void handleInputStream(InputStream is) throws Exception {
		String ext = getExtension();
		entryViewPanel.v(ext, is);
	}
	
	
	private String getExtension()
	{
		if(entry==null) return null;
		if(entry.endsWith("/")) return "#";
		if(entry.endsWith("\\")) return "#";
		if(!entry.contains(".")) return "";
		
		String[] n = entry.split("\\."); 
		return n[n.length-1];
	}
	
	
	private Icon icon(Object obj) {
		try {return (Icon) pathToIcon.t(obj);}
		catch (Exception e) {Outside.err(this,"icon(Object)",e);}
		return null;
	}
}
