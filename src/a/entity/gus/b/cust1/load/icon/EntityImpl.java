package a.entity.gus.b.cust1.load.icon;

import java.net.URL;

import javax.swing.ImageIcon;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210820";}

	
	private Service getConfigPath;
	private Service configIdToRoot;
	private Service pathToUrl;

	public EntityImpl() throws Exception {
		getConfigPath = Outside.service(this,"m008.t.config.loc.to.path");
		configIdToRoot = Outside.service(this,"m052.t.configid.to.root");
		pathToUrl = Outside.service(this,"m034.t.config.path.url");
	}
	
	public Object t(Object obj) throws Exception {
		String iconId = (String) obj;
		String configLoc = "icon/" + iconId + ".gif";
		
		String configPath = (String) getConfigPath.t(configLoc);
		URL configUrl = (URL) pathToUrl.t(configPath);
		if(configUrl!=null) return new ImageIcon(configUrl);
		
		String commonRoot = (String) configIdToRoot.t("gus.common");
		String commonPath = commonRoot + configLoc;
		URL commonUrl = (URL) pathToUrl.t(commonPath);
		if(commonUrl!=null) return new ImageIcon(commonUrl);
		
		return null;
	}
}
