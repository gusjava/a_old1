package a.entity.gus.b.entityclass1.cl.classpath.jarlist.config;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210829";}
	
	public static final String CONFIG_LOCATION = "list_jar";


	private Service loadText;
	private Service getArch;

	public EntityImpl() throws Exception {
		loadText = Outside.service(this,"gus.b.config1.load.text");
		getArch = Outside.service(this,"gus.a.jre.prop.arch.datamodel");
	}
	
	
	public Object g() throws Exception {

		String content = (String) loadText.t(CONFIG_LOCATION);
		String[] nn = content!=null ? content.split("\n") : new String[0];
		
		String arch = (String) getArch.g();
		for(int i=0;i<nn.length;i++)
		nn[i] = nn[i].replace("<arch>",arch);
		
		return nn;
	}
}
