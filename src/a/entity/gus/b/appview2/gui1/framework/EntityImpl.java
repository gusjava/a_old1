package a.entity.gus.b.appview2.gui1.framework;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I, ListSelectionListener {
	public String creationDate() {return "20210822";}

	public static final String ICONID = "FILE_java";
	public static final int FRAMEWORK_START = 12;

	private Service buildJList;
	private Service viewer;

	private File srcLocation;
	private Map map;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	

	public EntityImpl() throws Exception {
		buildJList = Outside.service(this,"gus.b.swing1.list.build.fromicon");
		viewer = Outside.service(this,"*gus.b.appview1.entryview");

		list = (JList) buildJList.t(ICONID);
		list.addListSelectionListener(this);

		labelNumber = new JLabel(" ");
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(labelNumber,BorderLayout.SOUTH);
		
		JSplitPane split = new JSplitPane();
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File appLocation = (File) o[0];
		List entries = (List) o[1];
		
		srcLocation = findSrcLocation(appLocation);
		
		updateGui(entries);
	}
	
	
	
	private File findSrcLocation(File appLocation) {
		if(!appLocation.isDirectory()) return appLocation;
		File root = appLocation.getParentFile();
		File srcDir = new File(root,"src");
		return srcDir.isDirectory() ? srcDir : null;
	}
	
	
	
	private void updateGui(List entries) throws Exception {
		
		map = new HashMap();
		int nb = entries.size();
		for(int i=0;i<nb;i++) {
			String entry = (String) entries.get(i);
			if(entry.endsWith(".class") && !entry.contains("$")) {
				String baseEntry = entry.substring(0, entry.length()-6);
				String javaEntry = baseEntry+".java";
				
				String path = baseEntry.replace("\\",".").replace("/",".");
				String path0 = path.substring(FRAMEWORK_START);
				
				if(srcLocation.isDirectory()) {
					File srcFile = new File(srcLocation, javaEntry);
					if(srcFile.isFile()) {
						map.put(path0, javaEntry);
					}
				}
				else if(entries.contains(javaEntry)) {
					map.put(path0, javaEntry);
				}
			}
		}
		
		Vector vec = new Vector(map.keySet());
		Collections.sort(vec);
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
		
		viewer.p(null);
	}
	
	
	public Object i() throws Exception {
		return panel;
	}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    
	

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {viewer.p(null);return;}
			
			String path = (String) list.getSelectedValue();
			String entry = (String) map.get(path);
			viewer.p(new Object[] {srcLocation, entry});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
