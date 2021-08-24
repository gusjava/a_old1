package a.entity.gus.b.appview2.gui3.entity;

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
	
	public static final String ICONID = "ELEMENT_entity";
	public static final int ENTITY_START = 9;

	private Service findSrcLocation;
	private Service buildJList;
	private Service entityViewer;
	
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
		entityViewer = Outside.service(this,"*gus.b.appview2.gui3.entity.detail");

		list = (JList) buildJList.t(ICONID);
		list.addListSelectionListener(this);

		labelNumber = new JLabel(" ");
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(labelNumber,BorderLayout.SOUTH);
		
		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(200);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) entityViewer.i());
		
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
					String entityName = findEntityName(path);
					if(!map.containsKey(entityName))
						map.put(entityName, new ArrayList());
					((List) map.get(entityName)).add(javaEntry);
				}
			}
		}
		
		Vector vec = new Vector(map.keySet());
		Collections.sort(vec);
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
		
		entityViewer.p(null);
	}
	
	
	private boolean javaEntryFound(String javaEntry) {
		if(srcLocation.isDirectory())
			return new File(srcLocation, javaEntry).isFile();
		return entries.contains(javaEntry);
	}
	
	private String findEntityName(String path) throws Exception {
		String[] nn = path.split("\\.");
		int len = nn[nn.length-1].length();
		return path.substring(ENTITY_START, path.length()-len-1);
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
			if(list.isSelectionEmpty()) {entityViewer.p(null);return;}
			
			String name = (String) list.getSelectedValue();
			List javaEntries = (List) map.get(name);
			
			entityViewer.p(new Object[] {srcLocation, javaEntries});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
