package a.core.gus.gyem.m011.g.param.outside;

import java.util.HashMap;
import java.util.Map;
import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.utils.UtilArgs;
import a.framework.G;

public class Module extends GyemSystem implements G {

	public static final String OE = "\\u152"; // character \\u152 = Å’
	
	private Map param;
	
	public Object g() throws Exception {
		if(param==null) init();
		return param;
	}
	
	private void init() throws Exception {
		String argsLine = UtilArgs.toString((String[]) get(MAIN_LAUNCH_ARGS));
		String[] args = argsLine.replace(";;",OE).split(";",-1);   
		
		param = new HashMap();
		for(int i=0;i<args.length;i++) if(args[i].contains("="))
		{
			String[] n = args[i].replace(OE,";").split("=",2);
			String key = n[0];
			String value = n[1];

			if(param.containsKey(key))
				throw new Exception("Param ["+key+"] is found many times inside args");
			param.put(key,value);
		}
	}
}
