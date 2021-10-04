package a.entity.gus.b.entitysrc2.compile.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.Service;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20211003";}
	
	public static final String COL_ID = 				"id";
	public static final String COL_ENTITYNAME = 		"entityname";
	public static final String COL_FILENAME = 			"filename";
	public static final String COL_DATE = 				"date";
	public static final String COL_LINE = 				"line";
	public static final String COL_LINENB = 			"linenb";
	public static final String COL_LINEPOS = 			"linepos";
	public static final String COL_TYPE = 				"type";
	public static final String COL_DESCRIPTION =		"description";

	
	private Service findEntityPackageDir;
	private Service findFrameworkPackageDir;
	private Service listingJava;
	private Service listingClass;
	private Service buildCmd;
	private Service findJavac;
	private Service insertRow;
	private Service deleteRows;
	
	private File defaultDir;
	

	public EntityImpl() throws Exception
	{
		findEntityPackageDir = Outside.service(this,"gus.a.entity.src.find.packagedir");
		findFrameworkPackageDir = Outside.service(this,"gus.a.framework.src.find.packagedir");
		listingJava = Outside.service(this,"gus.a.dir.listing0.files.java");
		listingClass = Outside.service(this,"gus.a.dir.listing0.files.class1");
		buildCmd = Outside.service(this,"gus.b.entitysrc2.compile.entity.buildcmd");
		findJavac = Outside.service(this,"gus.b.jdk1.dir.jdkdirs.latest.javac");
		insertRow = Outside.service(this,"gus.b.entitysrc2.database.entity_compile_err.insert");
		deleteRows = Outside.service(this,"gus.b.entitysrc2.database.entity_compile_err.delete");
		
		defaultDir = (File) Outside.resource(this,"defaultdir");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		
		File srcDir = (File) ((R) engine).r("rootDir");
		File binDir = (File) ((R) engine).r("binDir");
		File libDir = (File) ((R) engine).r("libDir");
		
		File srcEntityPackageDir = (File) findEntityPackageDir.t(new Object[] {srcDir, entityName});
		File binEntityPackageDir = (File) findEntityPackageDir.t(new Object[] {binDir, entityName});
		
		File[] entityJavaFiles = (File[]) listingJava.t(srcEntityPackageDir);
		
		if(isCompiled(entityJavaFiles, binEntityPackageDir)) return false;
		
		/*
		 * PREPARATION
		 */
		
		File[] entityClassFiles = (File[]) listingClass.t(binEntityPackageDir);
		if(entityClassFiles!=null) for(File entityClassFile : entityClassFiles) {
			entityClassFile.delete();
		}
		
		File srcFrameworkPackageDir = (File) findFrameworkPackageDir.t(srcDir);
		File[] frameworkJavaFiles = (File[]) listingJava.t(srcFrameworkPackageDir);
		
		File javacFile = (File) findJavac.g();
		
		File listingFile = new File(defaultDir,now()+".txt");
		PrintStream p = new PrintStream(listingFile);
		for(File f : frameworkJavaFiles) p.println(dd(f));
		for(File f : entityJavaFiles) p.println(dd(f));
		p.close();
		
		String[] cmdArray = (String[]) buildCmd.t(new File[] {javacFile, libDir, srcDir, binDir, listingFile});

		Connection cx = (Connection) ((R) engine).r("cx");
		deleteRows.p(new Object[] {cx, entityName});
		
		/*
		 * COMPILING
		 */
		
		System.out.println("Compiling entity: "+entityName);
		Date compileDate = new Date();
		
		ProcessBuilder pb = new ProcessBuilder(cmdArray);
		pb.redirectErrorStream(true);
		Process process = pb.start();
		
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String packagePath = srcEntityPackageDir.getAbsolutePath();
		int packagePathLen = packagePath.length();

		int k = 0;
		Map errMap = null;
		String line = null;
		while((line = br.readLine())!=null)
		{
			if(k==0 && line.startsWith(packagePath))
			{
				String line1 = line.substring(packagePathLen+1);
				String[] nn = line1.split(":",4);
				
				String fileName = nn[0];
				int lineNb = Integer.parseInt(nn[1]);
				String type = nn[2].trim();
				String desc = nn[3].trim();
				
				errMap = new HashMap();
				errMap.put(COL_ENTITYNAME, entityName);
				errMap.put(COL_FILENAME, fileName);
				errMap.put(COL_DATE, compileDate);
				errMap.put(COL_LINENB, lineNb);
				errMap.put(COL_TYPE, type);
				errMap.put(COL_DESCRIPTION, desc);
				k = 1;
			}
			else if(k==1)
			{
				errMap.put(COL_LINE, line.trim());
				k = 2;
			}
			else if(k==2)
			{
				int pos = line.indexOf("^");
				errMap.put(COL_LINEPOS, pos);
				System.out.println(errMap);
				insertRow.p(new Object[] {cx, errMap});
				k = 0;
			}
		}
		int exitCode = process.exitValue();
		System.out.println("exitCode: "+exitCode);
		
		is.close();
		listingFile.delete();
		
		return true;
	}
	
	
	
	
	private boolean isCompiled(File[] entityJavaFiles, File binEntityPackageDir) {
		for(File entityJavaFile : entityJavaFiles) {
			String fileName = entityJavaFile.getName();
			String fileName0 = fileName.substring(0, fileName.length()-5);
			File entityClassFile = new File(binEntityPackageDir, fileName0+".class");
			
			if(!entityClassFile.isFile()) return false;
			if(entityClassFile.lastModified() < entityJavaFile.lastModified()) return false;
		}
		return true;
	}

	
	
	
	private String now()
	{
		return new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
	}
	
	private String dd(File path)
	{return dd(path.getAbsolutePath());}


	private String dd(String value)
	{
		if(!value.contains(" ")) return value;
		value = value.replace(File.separator,"\\"+File.separator);
		value = value.replace(":","\\:");
		return value = "\""+value+"\"";
	}
}
