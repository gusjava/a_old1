package a.entity.gus.b.entitysrc2.perform.file.add;

import java.io.File;
import java.io.PrintStream;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210909";}

	public static final String MAIN_NAME = "EntityImpl";

	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];
		String className = (String) o[2];
		
		if(className.equals(MAIN_NAME)) return false;

		File rootDir = (File) ((R) engine).r("rootDir");
		String entityPackage = "a.entity."+entityName;
		
		File packageDir = new File(rootDir, entityPackage.replace(".",File.separator));
		if(!packageDir.isDirectory()) return false;
		
		String fileName = className + ".java";
		File javaFile = new File(packageDir, fileName);
		if(javaFile.exists()) return false;

		System.out.println("Adding java file "+fileName);
		System.out.println("- Src dir: "+rootDir);
		System.out.println("- Package: "+entityPackage);
		
		PrintStream p = new PrintStream(javaFile);
		
		p.println("package "+entityPackage+";");
		p.println();
		p.println("public class "+className+" {");
		p.println();
		p.println("\tpublic "+className+"()");
		p.println("\t{");
		p.println("\t\t");
		p.println("\t}");
		p.println();
		p.println("}");
		
		p.close();

		((V) engine).v("modified", entityName+"@"+className);
		return true;
	}
}
