package a.entity.gus.b.entitysrc2.gui.detail1.src.many;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.*;

public class EntityImpl implements Entity, P, I, ListSelectionListener {
	public String creationDate() {return "20210907";}
	
	public static final String ICONID = "FILE_java";
	public static final String MAIN_NAME = "EntityImpl";

	public static final String DISPLAY_ADD = "FILE_java_add#Add file [F1]";
	public static final String DISPLAY_DELETE = "FILE_java_delete#Delete file [DEL]";
	public static final String DISPLAY_RENAME = "FILE_java_rename#Rename file [F2]";
	public static final String DISPLAY_DUPLICATE = "FILE_java_duplicate#Duplicate file [F3]";
	
	
	private Service buildJList;
	private Service actionBuilder;
	private Service toolbarFactory;
	private Service getName0;
	private Service editor;

	private Service performAdd;
	private Service performDelete;
	private Service performRename;
	private Service performDuplicate;
	
	private Action actionAdd;
	private Action actionDelete;
	private Action actionRename;
	private Action actionDuplicate;

	private JPanel panel;
	private JList list;
	private JLabel labelNumber;
	private JToolBar bar;
	
	private String entityName;
	private Object engine;
	private File[] javaFiles;
	private Map map;

	public EntityImpl() throws Exception
	{
		buildJList = Outside.service(this,"gus.b.swing1.list.build.fromicon");
		actionBuilder = Outside.service(this,"gus.b.actions1.builder0");
		toolbarFactory = Outside.service(this,"gus.a.swing.toolbar.factory1");
		getName0 = Outside.service(this,"gus.a.file.getname0");
		editor = Outside.service(this,"*gus.b.entitysrc2.gui.javaeditor");
		
		performAdd = Outside.service(this,"gus.b.entitysrc2.perform.file.add.ask");
		performDelete = Outside.service(this,"gus.b.entitysrc2.perform.file.delete.ask");
		performRename = Outside.service(this,"gus.b.entitysrc2.perform.file.rename.ask");
		performDuplicate = Outside.service(this,"gus.b.entitysrc2.perform.file.duplicate.ask");
		
		actionAdd = (Action) actionBuilder.t(new Object[] {DISPLAY_ADD, (E) this::fileAdd});
		actionDelete = (Action) actionBuilder.t(new Object[] {DISPLAY_DELETE, (E) this::fileDelete});
		actionRename = (Action) actionBuilder.t(new Object[] {DISPLAY_RENAME, (E) this::fileRename});
		actionDuplicate = (Action) actionBuilder.t(new Object[] {DISPLAY_DUPLICATE, (E) this::fileDuplicate});
		

		labelNumber = new JLabel(" ");
		bar = (JToolBar) toolbarFactory.i();
		
		bar.add(actionAdd);
		bar.add(actionDelete);
		bar.add(actionRename);
		bar.add(actionDuplicate);
		
		list = (JList) buildJList.t(ICONID);
		list.addListSelectionListener(this);
		list.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(e.isControlDown()){
					if(code==KeyEvent.VK_C) filesCopy();
					else if(code==KeyEvent.VK_V) filesPaste();
				}
				else {
					if(code==KeyEvent.VK_DELETE) fileDelete();
					else if(code==KeyEvent.VK_F1) fileAdd();
					else if(code==KeyEvent.VK_F2) fileRename();
					else if(code==KeyEvent.VK_F3) fileDuplicate();
					else if(code==KeyEvent.VK_F5) _reload();
				}
			}
		});

		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(labelNumber,BorderLayout.CENTER);
		bottomPanel.add(bar,BorderLayout.EAST);
		
		JPanel panelLeft = new JPanel(new BorderLayout());
		panelLeft.add(new JScrollPane(list),BorderLayout.CENTER);
		panelLeft.add(bottomPanel,BorderLayout.SOUTH);
		
		JSplitPane split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(150);
		
		split.setLeftComponent(panelLeft);
		split.setRightComponent((JComponent) editor.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split, BorderLayout.CENTER);
		
		refreshActions();
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		engine = o[1];
		javaFiles = (File[]) o[2];
		
		reload();
	}
	
	
	
	private void _reload()
	{
		try {
			reload();
		}
		catch(Exception e) {
			Outside.err(this,"_reload()", e);
		}
	}
	
	
	private void reload() throws Exception
	{
		map = new HashMap();
		int nb = javaFiles.length;
		for(int i=0;i<nb;i++) {
			String fileName0 = (String) getName0.t(javaFiles[i]);
			map.put(fileName0, javaFiles[i]);
		}
		
		Vector vec = new Vector(map.keySet());
		if(!vec.contains(MAIN_NAME)) throw new Exception("EntityImpl class not found");
		vec.remove(MAIN_NAME);
		Collections.sort(vec);
		vec.add(0, MAIN_NAME);
		
		list.setListData(vec);
		labelNumber.setText(" "+vec.size());
		
		list.setSelectedIndex(0);
		refreshActions();
	}
	
	
	
	private void reset() throws Exception
	{
		entityName = null;
		engine = null;
		javaFiles = null;
		
		list.setListData(new Vector());
		labelNumber.setText(" ");
		editor.p(null);
		refreshActions();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	
	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty())
			{
				editor.p(null);
				refreshActions();
				return;
			}
			
			File javaFile = (File) map.get(getSelection());
			
			editor.p(new Object[] {entityName, engine, javaFile});
			refreshActions();
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	private String getSelection()
	{
		return (String) list.getSelectedValue();
	}
	
	
	private boolean hasSelection()
	{return !list.isSelectionEmpty();}
	
	
	private boolean hasSelectionOther()
	{return hasSelection() && !getSelection().equals(MAIN_NAME);}
	
	
	private boolean canModifyEntity() throws Exception
	{return permission("canModifyEntity");}
	
	
	private boolean permission(String permission) throws Exception
	{return ((F) engine).f(new Object[] {permission, entityName});}

	
	
	private void refreshActions() throws Exception
	{
		boolean hasSelectionOther = hasSelectionOther();
		boolean canModify = canModifyEntity();
		
		actionAdd.setEnabled(canModify);
		actionDelete.setEnabled(canModify && hasSelectionOther);
		actionRename.setEnabled(canModify && hasSelectionOther);
		actionDuplicate.setEnabled(canModify && hasSelectionOther);
	}
	
	
	
	
	
	
	/*
	 * ACTIONS GLOBALES
	 */
	
	private void fileAdd()
	{
		try {
			if(!canModifyEntity()) return;
			performAdd.p(new Object[] {engine, entityName, list});
		}
		catch(Exception e) {
			Outside.err(this, "fileAdd()", e);
		}
	}
	
	private void fileDelete()
	{
		try {
			if(!canModifyEntity()) return;
			if(!hasSelectionOther()) return;
			performDelete.p(new Object[] {engine, entityName, getSelection(), list});
		}
		catch(Exception e) {
			Outside.err(this, "fileDelete()", e);
		}
	}
	
	private void fileRename()
	{
		try {
			if(!canModifyEntity()) return;
			if(!hasSelectionOther()) return;
			performRename.p(new Object[] {engine, entityName, getSelection(), list});
		}
		catch(Exception e) {
			Outside.err(this, "fileRename()", e);
		}
	}
	
	private void fileDuplicate()
	{
		try {
			if(!canModifyEntity()) return;
			if(!hasSelectionOther()) return;
			performDuplicate.p(new Object[] {engine, entityName, getSelection(), list});
		}
		catch(Exception e) {
			Outside.err(this, "fileDuplicate()", e);
		}
	}
	
	
	private void filesCopy()
	{
		try {
			
		}
		catch(Exception e) {
			Outside.err(this, "filesCopy()", e);
		}
	}
	
	
	private void filesPaste()
	{
		try {
			if(!canModifyEntity()) return;
		}
		catch(Exception e) {
			Outside.err(this, "filesPaste()", e);
		}
	}
}
