package a.entity.gus.b.entitysrc2.gui.javaeditor;

import java.awt.BorderLayout;
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
import a.framework.V;

public class EntityImpl implements Entity, P, I, R, DocumentListener {
	public String creationDate() {return "20210907";}
	
	public static final String DISPLAY_SAVE = "ACTION_save#Save [Ctrl S]";
	public static final String DISPLAY_RELOAD = "ACTION_reload#Reload [Ctrl L]";

	
	private Service read;
	private Service actionBuilder;
	private Service custArea;
	private Service buildArea;
	private Service buildScroll;
	private Service getName0;
	
	private JPanel panel;
	private JTextArea area;
	private JScrollPane scroll;

	private Action actionSave;
	private Action actionReload;

	private Object holder;
	private File javaFile;
	
	private Object engine;
	private String entityName;
	private String fileName;
	
	private String text0;
	private boolean canModify;
	

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.a.file.string.read");
		actionBuilder = Outside.service(this,"gus.b.actions1.builder0");
		custArea = Outside.service(this,"gus.b.entitysrc2.gui.javaeditor.custcomp");
		buildArea = Outside.service(this,"gus.b.swing1.textarea1.factory");
		buildScroll = Outside.service(this,"gus.b.swing1.textarea.buildscrollpane.linenb");
		getName0 = Outside.service(this,"gus.a.file.getname0");
		
		actionSave = (Action) actionBuilder.t(new Object[] {DISPLAY_SAVE, (E) this::save_});
		actionReload = (Action) actionBuilder.t(new Object[] {DISPLAY_RELOAD, (E) this::reload_});
		
		area = (JTextArea) buildArea.i();
		custArea.p(area);
		
		area.getDocument().addDocumentListener(this);
		
		area.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(e.isControlDown()){
					if(code==KeyEvent.VK_S) save();
					else if(code==KeyEvent.VK_L) reload();
				}
			}
		});
		
		scroll = (JScrollPane) buildScroll.t(area);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		
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
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		holder = o[0];
		javaFile = (File) o[1];

		engine = ((R) holder).r("engine");
		entityName = (String) ((R) holder).r("entityName");
		fileName = (String) getName0.t(javaFile);
		
		canModify = canModifyEntity();
		text0 = (String) read.t(javaFile);

		area.getDocument().removeDocumentListener(this);
		area.setText(text0);
		area.getDocument().addDocumentListener(this);
		
		area.setEditable(canModify);
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
		
		int caretPosition = 0;
		String anchor = (String) ((R) holder).r("anchor");
		if(anchor!=null && anchor.startsWith(fileName+"@"))
			caretPosition = Integer.parseInt(anchor.split("@")[1]);
		setCaretPosition(caretPosition);

		SwingUtilities.invokeLater(()->area.requestFocusInWindow());
	}
	
	
	
	private void reset() throws Exception
	{
		holder = null;
		javaFile = null;
		engine = null;
		entityName = null;
		
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
	
	private void save()
	{
		try{save_();}
		catch(Exception e)
		{Outside.err(this,"save()",e);}
	}
	
	private void save_() throws Exception
	{
		if(!canModify) throw new Exception("canModify=false");
		if(text0==null) throw new Exception("text0==null");
		
		text0 = area.getText();
		
		PrintStream p = new PrintStream(javaFile);
		p.print(text0);
		p.close();
		
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
		
		((V) engine).v("modified",entityName+"@"+fileName+"@"+area.getCaretPosition());
	}
	
	
	
	
	/*
	 * RELOAD
	 */
	
	private void reload()
	{
		try{reload_();}
		catch(Exception e)
		{Outside.err(this,"reload()",e);}
	}
	
	private void reload_() throws Exception
	{
		if(!canModify) throw new Exception("canModify=false");
		if(text0==null) throw new Exception("text0==null");
		
		text0 = (String) read.t(javaFile);

		int caretPosition = area.getCaretPosition();
		area.getDocument().removeDocumentListener(this);
		
		area.setText(text0);
		
		area.getDocument().addDocumentListener(this);
		setCaretPosition(caretPosition);
		
		actionSave.setEnabled(false);
		actionReload.setEnabled(false);
		
		SwingUtilities.invokeLater(()->area.requestFocusInWindow());
	}
	
	
	/*
	 * CARET POSITION
	 */
	
	private void setCaretPosition(int caretPosition) {
		int len = area.getText().length();
		if(caretPosition>len) caretPosition = len;
		area.setCaretPosition(caretPosition);
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
		if(r==JOptionPane.YES_OPTION) save_();
	}
}
