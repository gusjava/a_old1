package a.entity.gus.b.jdk1.dir.jdkdirs.latest;

import java.io.File;
import java.util.Comparator;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210929";}

	
	private Service jdkDirs;
	private JdkComparator comparator = new JdkComparator();
	
	
	private File dir;

	public EntityImpl() throws Exception
	{
		jdkDirs = Outside.service(this,"gus.b.jdk1.dir.jdkdirs");
	}
	
	
	public Object g() throws Exception
	{
		if(dir==null) init();
		return dir;
	}
	
	
	private void init() throws Exception
	{
		File[] dirs = (File[]) jdkDirs.g();
		if(dirs==null || dirs.length==0) return;
		
		dir = dirs[0];
		for(int i=1;i<dirs.length;i++)
		if(comparator.compare(dirs[i],dir)==1) dir = dirs[i];
	}
	
	
	
	
	
	
	public class JdkComparator implements Comparator
	{
		public int compare(Object jdk1, Object jdk2)
		{
			File jdkFile1 = (File) jdk1;
			File jdkFile2 = (File) jdk2;
			
			String jdkVersion1 = jdkFile1.getName().substring(3);
			String jdkVersion2 = jdkFile2.getName().substring(3);
			
			if(jdkVersion1.equals(jdkVersion2)) return 0;
			
			int[] n1 = parseJavaVersion(jdkVersion1);
			int[] n2 = parseJavaVersion(jdkVersion2);
			
			return compareArrays(n1,n2);
		}
		
		private int convertToInt(String value)
		{
			try{return Integer.parseInt(value);}
			catch(Exception e){return 0;}
		}
		
		private int[] parseJavaVersion(String javaVersion)
		{
			javaVersion = javaVersion.replaceAll("[_\\.-]"," ").trim();
			
			String[] info = javaVersion.split(" +");
			int[] weigth = new int[info.length];
			for(int i=0;i<info.length;i++)
			weigth[i] = convertToInt(info[i]);
			return weigth;
		}
		
		private int compareArrays(int[] a, int[] b)
		{
			for(int i=0;i<Math.min(a.length,b.length);i++)
			{
				if(a[i]>b[i]) return 1;
				if(a[i]<b[i]) return -1;
			}
			return 0;
		}
	}
}
