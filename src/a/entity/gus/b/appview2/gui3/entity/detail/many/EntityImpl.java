package a.entity.gus.b.appview2.gui3.entity.detail.many;

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

import a.framework.*;

public class EntityImpl implements Entity, P, I, ListSelectionListener {
	public String creationDate() {return "20210824";}


	public static final String ICONID = "FILE_java";

	private Service buildJList;
	private Service srcViewer;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;

	private File srcLocation;
	private List entries;
	private Map map;
	

	public EntityImpl() throws Exception {
		buildJList = Outside.service(this,"gus.b.swing1.list.build.fromicon");
		srcViewer = Outside.service(this,"*gus.b.appview1.entryview");

		list = (JList) buildJList.t(ICONID);
		list.addListSelectionListener(this);

		labelNumber = new JLabel(" ");
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(labelNumber,BorderLayout.SOUTH);
		
		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(150);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) srcViewer.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception {
		if(obj==null) {resetGui();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		srcLocation = (File) o[0];
		entries = (List) o[1];
		
		map = new HashMap();
		int nb = entries.size();
		for(int i=0;i<nb;i++) {
			String javaEntry = (String) entries.get(i);
			String baseEntry = javaEntry.substring(0, javaEntry.length()-5);
			String path = baseEntry.replace("\\",".").replace("/",".");
			String path0 = findPath0(path);
			map.put(path0, javaEntry);
		}
		
		Vector vec = new Vector(map.keySet());
		Collections.sort(vec);
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
		
		srcViewer.p(null);
	}
	
	
	private void resetGui() throws Exception {
		list.setListData(new Vector());
		labelNumber.setText(" ");
		srcViewer.p(null);
	}
	
	
	private String findPath0(String path) {
		String[] nn = path.split("\\.");
		return nn[nn.length-1];
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
			if(list.isSelectionEmpty()) {srcViewer.p(null);return;}
			
			String path0 = (String) list.getSelectedValue();
			String javaEntry = (String) map.get(path0);
			
			srcViewer.p(new Object[] {srcLocation, javaEntry});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
