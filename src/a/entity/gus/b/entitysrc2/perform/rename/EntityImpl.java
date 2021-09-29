package a.entity.gus.b.entitysrc2.perform.rename;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210908";}


	private Service findPackageDir;
	private Service findJavaFiles;
	private Service validate;
	private Service read;

	public EntityImpl() throws Exception
	{
		findPackageDir = Outside.service(this, "gus.a.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.a.dir.listing0.files.java");
		validate = Outside.service(this,"gus.a.entity.name.validate");
		read = Outside.service(this, "gus.a.file.string.read");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String name0 = (String) o[1];
		String name1 = (String) o[2];
		
		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");
		
		if(devId!=null && !name0.startsWith(devId+".")) return false;
		
		if(devId!=null && !name1.startsWith(devId+".")) 
			name1 = devId+"."+name1;
		
		if(!validate.f(name1)) return false;
		
		File packageDir0 = (File) findPackageDir.t(new Object[] {rootDir, name0});
		File packageDir1 = (File) findPackageDir.t(new Object[] {rootDir, name1});
		
		if(new File(packageDir1,"EntityImpl.java").exists()) return false;
		
		File[] javaFiles0 = (File[]) findJavaFiles.t(packageDir0);

		System.out.println("Renaming entity "+name0+" into "+name1);
		System.out.println("- Src dir: "+rootDir);
		System.out.println("- Java file nb: "+javaFiles0.length);
		
		packageDir1.mkdirs();
		
		for(File javaFile0 : javaFiles0) {
			File javaFile1 = new File(packageDir1, javaFile0.getName());
			transfer(javaFile0, javaFile1, name0, name1);
		}
		
		for(File javaFile0 : javaFiles0) {
			Files.deleteIfExists(javaFile0.toPath());
		}
		cleanDir(packageDir0);
		
		((V) engine).v("renamed",new String[] {name0, name1});
		return true;
	}
	
	
	private void transfer(File f0, File f1, String name0, String name1) throws Exception {
		String src = (String) read.t(f0);
		
		src = src.replace("package a.entity."+name0+";", "package a.entity."+name1+";");

		PrintStream p = new PrintStream(f1);
		p.print(src);
		p.close();
	}
	
	
	
	private void cleanDir(File dir) throws Exception {
		while(dir!=null && dir.isDirectory() && dir.list()!=null && dir.list().length==0) {
			Files.deleteIfExists(dir.toPath());
			dir = dir.getParentFile();
		}
	}
}
