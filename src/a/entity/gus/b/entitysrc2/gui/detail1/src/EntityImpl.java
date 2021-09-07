package a.entity.gus.b.entitysrc2.gui.detail1.src;

import java.io.File;
import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210907";}


	private Service shiftPanel;
	private Service editor;
	private Service panelMany;
	private Service findPackageDir;
	private Service findJavaFiles;
	
	private String entityName;
	private Object engine;
	
	private File rootDir;
	private File packageDir;
	private File[] javaFiles;
	
	
	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.a.swing.panel.shiftpanel");
		editor = Outside.service(this,"*gus.b.entitysrc2.gui.javaeditor");
		panelMany = Outside.service(this,"*gus.b.entitysrc2.gui.javaeditor.many");
		findPackageDir = Outside.service(this,"gus.a.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this,"gus.a.dir.listing0.files.java");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		engine = o[1];
		
		rootDir = (File) ((R) engine).r("rootDir");
		packageDir = (File) findPackageDir.t(new Object[]{rootDir, entityName});
		javaFiles = (File[]) findJavaFiles.t(packageDir);
		
		if(javaFiles.length==1) {
			editor.p(new Object[] {entityName, engine, javaFiles[0]});
			shiftPanel.p(editor);
		}
		else {
			panelMany.p(new Object[] {entityName, engine, javaFiles});
			shiftPanel.p(panelMany);
		}
	}
	
	
	private void reset() throws Exception
	{
		entityName = null;
		engine = null;
		rootDir = null;
		packageDir = null;
		javaFiles = null;

		editor.p(null);
		panelMany.p(null);
		shiftPanel.p(null);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
}
