package a.entity.gus.b.entitysrc2.perform.refactor.downlinks;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210929";}

	private Service findEntityFile;
	private Service findDownLinks;
	private Service read;
	private Service extract;

	public EntityImpl() throws Exception
	{
		findEntityFile = Outside.service(this,"gus.a.entity.src.find.entityfile");
		findDownLinks = Outside.service(this,"gus.b.entitysrc2.database.entity_link.find2");
		read = Outside.service(this, "gus.a.file.string.read");
		extract = Outside.service(this, "gus.a.entity.name.extract");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String name0 = (String) o[1];
		String name1 = (String) o[2];
		
		Connection cx = (Connection) ((R) engine).r("cx");
		File rootDir = (File) ((R) engine).r("rootDir");
		Pattern regex = Pattern.compile((String) extract.g(), Pattern.DOTALL);
		
		Set downLinks = (Set) findDownLinks.t(new Object[] {cx, name0});
		Iterator it = downLinks.iterator();
		while(it.hasNext())
		{
			String downLink = (String) it.next();
			File entityFile = (File) findEntityFile.t(new Object[]{rootDir, downLink});
			
			if(entityFile.isFile())
			{
				String src = (String) read.t(entityFile);
				StringBuffer newSrc = new StringBuffer();
				
				Matcher m = regex.matcher(src);
				
				while(m.find())
				{
					String l = m.group();
					if(l.equals(name0))
					{
						System.out.println(downLink+": Refactoring link "+l+" into "+name1);
						l = name1;
					}
					m.appendReplacement(newSrc,l);
				}
				m.appendTail(newSrc);
				
				PrintStream p = new PrintStream(entityFile);
				p.print(newSrc.toString());
				p.close();
			}
		}
	}
}
