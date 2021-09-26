package a.entity.gus.a.entity.name.extract;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.Entity;
import a.framework.G;
import a.framework.T;

public class EntityImpl implements Entity, T, G {
	public String creationDate() {return "20210913";}
	
	public static final String REGEX = "[a-z][a-z0-9]{2,9}(\\.[a-z][a-z0-9_]*)+";
	
	private Pattern p;
	
	public EntityImpl()
	{
		p = Pattern.compile(REGEX, Pattern.DOTALL);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		List list = new ArrayList();
		Matcher m = p.matcher(src);
		while(m.find()) list.add(m.group());
		return list;
	}

	public Object g() throws Exception
	{return REGEX;}
}
