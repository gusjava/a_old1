package a.entity.gus.a.io.build.string;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}


	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		StringBuilder sb = new StringBuilder();

		InputStreamReader isr = null;
		BufferedReader br = null;

		try
		{
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
		
			String line = null;
			while((line = br.readLine())!=null) sb.append(line+"\n");
		}
		finally
		{
			if(br!=null) br.close();
		}
		return sb.toString();
	}
}