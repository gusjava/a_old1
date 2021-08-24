package a.entity.gus.b.appview2.gui2.core;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
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

import a.framework.*;

public class EntityImpl implements Entity, P, I, ListSelectionListener {
	public String creationDate() {return "20210822";}
	
	public static final String ICONID = "core";
	public static final int CORE_START = 7;

	private Service findSrcLocation;
	private Service buildJList;
	private Service coreViewer;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;

	private File appLocation;
	private File srcLocation;
	private List entries;
	private Map map;
	

	public EntityImpl() throws Exception {
		findSrcLocation = Outside.service(this,"gus.b.appview2.find.srclocation");
		buildJList = Outside.service(this,"gus.b.swing1.list.build.fromicon");
		coreViewer = Outside.service(this,"*gus.b.appview2.gui2.core.detail");

		list = (JList) buildJList.t(ICONID);
		list.addListSelectionListener(this);

		labelNumber = new JLabel(" ");
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(labelNumber,BorderLayout.SOUTH);
		
		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(100);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) coreViewer.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		appLocation = (File) o[0];
		entries = (List) o[1];

		srcLocation = (File) findSrcLocation.t(appLocation);
		
		map = new HashMap();
		int nb = entries.size();
		for(int i=0;i<nb;i++) {
			String entry = (String) entries.get(i);
			if(entry.endsWith(".class") && !entry.contains("$")) {
				String baseEntry = entry.substring(0, entry.length()-6);
				String javaEntry = baseEntry+".java";
				String path = baseEntry.replace("\\",".").replace("/",".");
				
				if(javaEntryFound(javaEntry)) {
					String coreName = findCoreName(path);
					if(!map.containsKey(coreName))
						map.put(coreName, new ArrayList());
					((List) map.get(coreName)).add(javaEntry);
				}
			}
		}
		
		Vector vec = new Vector(map.keySet());
		Collections.sort(vec);
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
		
		coreViewer.p(null);
	}
	
	
	private boolean javaEntryFound(String javaEntry) {
		if(srcLocation.isDirectory())
			return new File(srcLocation, javaEntry).isFile();
		return entries.contains(javaEntry);
	}
	
	private String findCoreName(String path) throws Exception {
		String[] nn = path.substring(CORE_START).split("\\.");
		if(nn.length<3) throw new Exception("Invalid class path for core component: "+path);
		return nn[0]+"."+nn[1];
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
			if(list.isSelectionEmpty()) {coreViewer.p(null);return;}
			
			String name = (String) list.getSelectedValue();
			List javaEntries = (List) map.get(name);
			
			coreViewer.p(new Object[] {srcLocation, javaEntries});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
