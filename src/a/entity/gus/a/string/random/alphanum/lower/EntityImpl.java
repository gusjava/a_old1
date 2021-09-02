package a.entity.gus.a.string.random.alphanum.lower;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210901";}


	public Object t(Object obj) throws Exception
	{
		int length = Integer.parseInt(""+obj);
		return randomString(length);
	}
	
	
	private String randomString(int length)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++) b.append(randomChar());
		return b.toString();
	}
	
	
	private char randomChar()
	{
		int r = random(36); //26+10
		if(r<10) return (char)(48+r);
		return (char)(87+r);//97-10
	}
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
