package a.entity.gus.b.entitysrc2.gui.javaeditor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintStream;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, I, R, DocumentListener {
	public String creationDate() {return "20210907";}
	
	public static final String DISPLAY_SAVE = "ACTION_save#Save [Ctrl S]";
	public static final String DISPLAY_RELOAD = "ACTION_reload#Reload [Ctrl L]";

	
	private Service read;
	private Service actionBuilder;
	
	private JPanel panel;
	private JTextArea area;

	private Action actionSave;
	private Action actionReload;

	private String entityName;
	private Object engine;
	private File javaFile;
	
	private String text0;
	private boolean canModify;
	

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.a.file.string.read");
		actionBuilder = Outside.service(this,"gus.b.actions1.builder0");
		
		actionSave = (Action) actionBuilder.t(new Object[] {DISPLAY_SAVE, (E) this::save});
		actionReload = (Action) actionBuilder.t(new Object[] {DISPLAY_RELOAD, (E) this::reload});
		
		area = new JTextArea();
		area.setMargin(new Insets(3, 3, 3, 3));
		area.getDocument().addDocumentListener(this);
		
		area.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(e.isControlDown()){
					if(code==KeyEvent.VK_S) _save();
					else if(code==KeyEvent.VK_L) _reload();
				}
			}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		
		canModify = false;
		text0 = null;
		
		area.setEditable(false);
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		complete();
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		engine = o[1];
		javaFile = (File) o[2];
		
		canModify = canModifyEntity();
		text0 = (String) read.t(javaFile);

		area.getDocument().removeDocumentListener(this);
		area.setText(text0);
		area.getDocument().addDocumentListener(this);
		area.setCaretPosition(0);
		
		area.setEditable(canModify);
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
	}
	
	
	
	private void reset() throws Exception
	{
		entityName = null;
		engine = null;
		javaFile = null;
		
		canModify = false;
		text0 = null;

		area.getDocument().removeDocumentListener(this);
		area.setText("");
		area.getDocument().addDocumentListener(this);
		
		area.setEditable(false);
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("actionSave")) return actionSave;
		if(key.equals("actionReload")) return actionReload;
		if(key.equals("keys")) return new String[] {"actionSave", "actionReload"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	private boolean canModifyEntity() throws Exception
	{return permission("canModifyEntity");}
	
	
	private boolean permission(String permission) throws Exception
	{return engine!=null && ((F) engine).f(new Object[] {permission, entityName});}
	


	public void insertUpdate(DocumentEvent e) {changed();}
	public void removeUpdate(DocumentEvent e) {changed();}
	public void changedUpdate(DocumentEvent e) {}
	
	
	
	
	private void changed()
	{
		try
		{
			if(!canModify) throw new Exception("canModify=false");
			if(text0==null) throw new Exception("text0==null");
			
			boolean synchWithFile = area.getText().equals(text0);
			actionSave.setEnabled(!synchWithFile);
			actionReload.setEnabled(!synchWithFile);
		}
		catch(Exception e)
		{
			Outside.err(this,"changed()",e);
		}
	}
	
	
	
	/*
	 * SAVE
	 */
	
	private void _save()
	{
		try{save();}
		catch(Exception e)
		{Outside.err(this,"_save()",e);}
	}
	
	private void save() throws Exception
	{
		if(!canModify) throw new Exception("canModify=false");
		if(text0==null) throw new Exception("text0==null");
		
		text0 = area.getText();
		
		PrintStream p = new PrintStream(javaFile);
		p.print(text0);
		p.close();
		
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
	}
	
	
	
	
	/*
	 * RELOAD
	 */
	
	private void _reload()
	{
		try{reload();}
		catch(Exception e)
		{Outside.err(this,"_reload()",e);}
	}
	
	private void reload() throws Exception
	{
		if(!canModify) throw new Exception("canModify=false");
		if(text0==null) throw new Exception("text0==null");
		
		text0 = (String) read.t(javaFile);

		area.getDocument().removeDocumentListener(this);
		area.setText(text0);
		area.getDocument().addDocumentListener(this);
		
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
	}
	
	
	/*
	 * COMPLETE
	 */
	
	public static final String TITLE = "Save changes ?";
	public static final String MESSAGE = "Please, confirm saving";
	
	private void complete() throws Exception
	{
		if(!canModify) return;
		if(text0==null) return;
		if(area.getText().equals(text0)) return;
		
		Window window = SwingUtilities.getWindowAncestor(area);
		int r = JOptionPane.showConfirmDialog(window, MESSAGE, TITLE, JOptionPane.YES_NO_OPTION);
		if(r==JOptionPane.YES_OPTION) save();
	}
}
