package a.entity.gus.b.entitysrc2.gui.javaeditor.many;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
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
	public String creationDate() {return "20210907";}


	public static final String ICONID = "FILE_java";

	private Service buildJList;
	private Service getName0;
	private Service editor;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	
	private String entityName;
	private Object engine;
	private File[] javaFiles;
	private Map map;

	public EntityImpl() throws Exception
	{
		buildJList = Outside.service(this,"gus.b.swing1.list.build.fromicon");
		getName0 = Outside.service(this,"gus.a.file.getname0");
		editor = Outside.service(this,"*gus.b.entitysrc2.gui.javaeditor");
		
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
		split.setRightComponent((JComponent) editor.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		engine = o[1];
		javaFiles = (File[]) o[2];

		map = new HashMap();
		int nb = javaFiles.length;
		for(int i=0;i<nb;i++) {
			String fileName0 = (String) getName0.t(javaFiles[i]);
			map.put(fileName0, javaFiles[i]);
		}
		
		Vector vec = new Vector(map.keySet());
		if(!vec.contains("EntityImpl")) throw new Exception("EntityImpl class not found");
		vec.remove("EntityImpl");
		Collections.sort(vec);
		vec.add(0,"EntityImpl");
		
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
		
		list.setSelectedIndex(0);
	}
	
	
	
	private void reset() throws Exception
	{
		entityName = null;
		engine = null;
		javaFiles = null;
		
		list.setListData(new Vector());
		labelNumber.setText(" ");
		editor.p(null);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {editor.p(null);return;}
			
			String fileName = (String) list.getSelectedValue();
			File javaFile = (File) map.get(fileName);
			
			editor.p(new Object[] {entityName, engine, javaFile});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
