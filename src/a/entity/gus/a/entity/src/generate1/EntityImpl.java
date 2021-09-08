package a.entity.gus.a.entity.src.generate1;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210906";}
	
	public static final String CLASS_NAME = "EntityImpl";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}

	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = (File) o[0];
		String entityName = (String) o[1];
		String features = (String) o[2];
		
		
		features = features.toUpperCase();
		
		String entityPackage = "a.entity."+entityName;
		
		
		File packageDir = new File(rootDir, entityPackage.replace(".",File.separator));
		packageDir.mkdirs();
		
		File javaFile = new File(packageDir, CLASS_NAME + ".java");
		if(javaFile.exists()) return false;
		
		
		System.out.println("Generating entity "+entityName);
		System.out.println("- Src dir: "+rootDir);
		System.out.println("- Features: "+features);
		
		boolean isE = features.contains("E");
		boolean isG = features.contains("G");
		boolean isP = features.contains("P");
		boolean isT = features.contains("T");
		boolean isB = features.contains("B");
		boolean isF = features.contains("F");
		boolean isH = features.contains("H");
		boolean isI = features.contains("I");
		boolean isR = features.contains("R");
		boolean isV = features.contains("V");
		boolean isS = features.contains("S");
		
		PrintStream p = new PrintStream(javaFile);
		
		p.println("package "+entityPackage+";");
		p.println();
		p.println("import a.framework.*;");
		p.println();
		p.print("public class ");
		p.print(CLASS_NAME);
		if(isS) p.print(" extends S1");
		p.print(" implements Entity");
		if(isE) p.print(", E");
		if(isG) p.print(", G");
		if(isP) p.print(", P");
		if(isT) p.print(", T");
		if(isB) p.print(", B");
		if(isF) p.print(", F");
		if(isH) p.print(", H");
		if(isI) p.print(", I");
		if(isR) p.print(", R");
		if(isV) p.print(", V");
		p.println(" {");
		p.println("\tpublic String creationDate() {return \""+today()+"\";}");
		p.println();
		p.println();
		p.println("\tpublic EntityImpl() throws Exception");
		p.println("\t{");
		p.println("\t\t");
		p.println("\t}");
		
		if(isE)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic void e() throws Exception");
			p.println("\t{");
			p.println("\t\t");
			p.println("\t}");
		}
		
		if(isG)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object g() throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}
		
		if(isP)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic void p(Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\t");
			p.println("\t}");
		}
		
		if(isT)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object t(Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}
		
		if(isB)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic boolean b() throws Exception");
			p.println("\t{");
			p.println("\t\treturn true;");
			p.println("\t}");
		}
		
		if(isF)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic boolean f(Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\treturn true;");
			p.println("\t}");
		}
		
		if(isH)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic double h(double value) throws Exception");
			p.println("\t{");
			p.println("\t\treturn 0;");
			p.println("\t}");
		}
		
		if(isI)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object i() throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}
		
		if(isR)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object r(String key) throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}
		
		if(isV)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic void v(String key, Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\t");
			p.println("\t}");
		}
		
		p.println("}");
		p.close();
		
		return true;
	}
}
