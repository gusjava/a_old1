package a.entity.gus.b.apprestart1.perform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20211004";}


	private Service findAppLocation;
	private Service findJavaExe;
	private Service findClasspath;
	private Service exit;
	
	private String[] args;
	private Class mainClass;
	

	public EntityImpl() throws Exception
	{
		findAppLocation = Outside.service(this,"gus.a.app.location");
		findJavaExe = Outside.service(this,"gus.b.jre1.dir.bin.javaexe");
		findClasspath = Outside.service(this,"gus.a.jre.prop.classpath.as.filearray");
		
		exit = Outside.service(this,"gus.b.cust1.exit");
		
		args = (String[]) Outside.resource(this,"launch.args");
		mainClass = (Class) Outside.resource(this,"launch.class");
	}
	
	
	public void e() throws Exception
	{
		File location = (File) findAppLocation.g();
		File javaExe = (File) findJavaExe.g();
		File[] classpath = (File[]) findClasspath.g();
		
		System.out.println("args: "+args);
		System.out.println("mainClass: "+mainClass);
		System.out.println("location: "+location);
		System.out.println("javaExe: "+javaExe);
		
		List cmd = new ArrayList();
		cmd.add(javaExe.getAbsolutePath());
		
		if(location.isFile())
		{
			cmd.add("-jar");
			cmd.add(location.getAbsolutePath());
		}
		
		if(classpath.length>0)
		{
			cmd.add("-cp");
			cmd.add(joinSemicolon(classpath));
		}
		
		cmd.add(mainClass.getName());
		
		if(args!=null) 
		{
			for(String arg:args) 
			cmd.add(arg);
		}
		
		ProcessBuilder pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(true);
		pb.start();
		
		exit.e();
		System.exit(0);
	}
	
	
	
	private String joinSemicolon(File[] classpath)
	{
		StringJoiner sj = new StringJoiner(";");
		for(File f : classpath) sj.add(f.getAbsolutePath());
		return sj.toString();
	}
}
