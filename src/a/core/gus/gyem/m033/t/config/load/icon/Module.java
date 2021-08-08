package a.core.gus.gyem.m033.t.config.load.icon;

import java.net.URL;

import javax.swing.ImageIcon;

import a.core.gus.gyem.GyemSystem;
import a.framework.T;

public class Module extends GyemSystem implements T {
	
	public Object t(Object obj) throws Exception {
		String iconId = (String) obj;
		String configLoc = CONFIGLOC_ICON + iconId + ".gif";
		String configPath = (String) moduleT(M008_T_CONFIG_LOC_TO_PATH).t(configLoc);
		
		URL url = (URL) moduleT(M034_T_CONFIG_PATH_URL).t(configPath);
		return url!=null ? new ImageIcon(url) : null;
	}
}
