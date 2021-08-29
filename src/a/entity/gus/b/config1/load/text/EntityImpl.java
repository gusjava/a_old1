package a.entity.gus.b.config1.load.text;

import java.io.InputStream;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}

	
	private Service getConfigPath;
	private Service configIdToRoot;
	private Service pathToInputStream;
	private Service inputStreamToText;

	public EntityImpl() throws Exception {
		getConfigPath = Outside.service(this,"m008.t.config.loc.to.path");
		configIdToRoot = Outside.service(this,"m052.t.configid.to.root");
		pathToInputStream = Outside.service(this,"m007.t.config.path.inputstream");
		inputStreamToText = Outside.service(this,"gus.a.io.build.string");
	}
	
	public Object t(Object obj) throws Exception {
		String configLoc = (String) obj;
		
		String configPath = (String) getConfigPath.t(configLoc);
		InputStream configIs = (InputStream) pathToInputStream.t(configPath);
		if(configIs!=null) return inputStreamToText(configIs);
		
		String commonRoot = (String) configIdToRoot.t("gus.common");
		String commonPath = commonRoot + configLoc;
		InputStream commonIs = (InputStream) pathToInputStream.t(commonPath);
		if(commonIs!=null) return inputStreamToText(commonIs);
		
		return null;
	}
	
	private String inputStreamToText(InputStream is) throws Exception {
		return (String) inputStreamToText.t(is);
	}
}
