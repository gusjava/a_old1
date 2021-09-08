package a.entity.gus.b.entitysrc2.perform.delete;

import java.io.File;
import java.nio.file.Files;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20210908";}


	private Service findPackageDir;
	private Service findJavaFiles;

	public EntityImpl() throws Exception
	{
		findPackageDir = Outside.service(this, "gus.a.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.a.dir.listing0.files.java");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String entityName = (String) o[1];

		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");
		
		if(devId!=null && !entityName.startsWith(devId+".")) return false;
		
		File packageDir = (File) findPackageDir.t(new Object[] {rootDir, entityName});
		if(!packageDir.isDirectory()) return false;
		
		File[] javaFiles = (File[]) findJavaFiles.t(packageDir);
		if(javaFiles==null || javaFiles.length==0) return false;

		System.out.println("Deleting entity "+entityName);
		System.out.println("- Src dir: "+rootDir);
		System.out.println("- Java file nb: "+javaFiles.length);
		
		for(File javaFile : javaFiles) {
			Files.deleteIfExists(javaFile.toPath());
		}
		cleanDir(packageDir);
		
		((E) engine).e();
		return true;
	}
	
	
	
	private void cleanDir(File dir) throws Exception {
		while(dir!=null && dir.isDirectory() && dir.list()!=null && dir.list().length==0) {
			Files.deleteIfExists(dir.toPath());
			dir = dir.getParentFile();
		}
	}
}
