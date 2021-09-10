package a.entity.gus.b.entitysrc2.gui.detail1.src.single;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210909";}

	public static final String DISPLAY_ADD = "FILE_java_add#Add file";
	

	private Service editor;
	private Service actionBuilder;
	private Service toolbarFactory;
	private Service performAdd;

	private Action actionAdd;
	
	private JPanel panel;
	private JToolBar bar;
	
	private String entityName;
	private Object engine;
	private File javaFile;

	public EntityImpl() throws Exception
	{
		actionBuilder = Outside.service(this,"gus.b.actions1.builder0");
		toolbarFactory = Outside.service(this,"gus.a.swing.toolbar.factory1");
		editor = Outside.service(this,"*gus.b.entitysrc2.gui.javaeditor");
		performAdd = Outside.service(this,"gus.b.entitysrc2.perform.file.add.ask");
		
		actionAdd = (Action) actionBuilder.t(new Object[] {DISPLAY_ADD, (E) this::fileAdd});
		
		bar = (JToolBar) toolbarFactory.i();
		bar.setOrientation(JToolBar.VERTICAL);
		bar.add(actionAdd);
		
		panel = new JPanel(new BorderLayout());
		panel.add(bar, BorderLayout.WEST);
		panel.add((JComponent) editor.i(), BorderLayout.CENTER);
		
		actionAdd.setEnabled(false);
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		engine = o[1];
		javaFile = (File) o[2];

		actionAdd.setEnabled(canModifyEntity());
		editor.p(new Object[] {entityName, engine, javaFile});
	}
	
	
	
	private void reset() throws Exception
	{
		entityName = null;
		engine = null;
		javaFile = null;
		
		actionAdd.setEnabled(false);
		editor.p(null);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private boolean canModifyEntity() throws Exception
	{return permission("canModifyEntity");}
	
	
	private boolean permission(String permission) throws Exception
	{return engine!=null && ((F) engine).f(new Object[] {permission, entityName});}
	
	
	
	private void fileAdd()
	{
		try {
			if(!canModifyEntity()) return;
			performAdd.p(new Object[] {engine, entityName, bar});
		}
		catch(Exception e) {
			Outside.err(this, "fileAdd()", e);
		}
	}
}
