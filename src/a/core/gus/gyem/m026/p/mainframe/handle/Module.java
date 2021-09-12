package a.core.gus.gyem.m026.p.mainframe.handle;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.utils.UtilLog;
import a.framework.P;

public class Module extends GyemSystem implements P {

	public void p(Object obj) throws Exception {
		JFrame frame = (JFrame) obj;
		
		Container content = (Container) moduleG(M027_G_MAINFRAME_CONTENT).g();
		frame.setContentPane(content);
		
		Map prop = (Map) moduleG(M003_G_PROP).g();
		
		String title = prop(prop, PROP_APP_TITLE, DEFAULTPROP_APP_TITLE);
		String size = prop(prop, PROP_APP_SIZE, DEFAULTPROP_APP_SIZE);
		String iconId = prop(prop, PROP_APP_ICON, null);

		frame.setTitle(title);
		frame.setSize(dim(size));
		frame.setIconImage(toImage(iconId));
		
		frame.setLocationRelativeTo(null);
		
		UtilLog.println("Displaying main frame...");
		frame.setVisible(true);
		UtilLog.println("Main frame displayed");
	}
	
	
	
	private String prop(Map prop, String key, String defaultValue) {
		if(!prop.containsKey(key)) return defaultValue;
		return (String) prop.get(key);
	}
	
	private Dimension dim(String size) {
		String[] n = size.split(" ");
		return new Dimension(i_(n[0]),i_(n[1]));
	}
	
	private int i_(String s){
		return Integer.parseInt(s);
	}
	
	private Image toImage(String iconId) throws Exception {
		if(iconId==null) return null;
		ImageIcon icon = (ImageIcon) moduleT(M033_T_CONFIG_LOAD_ICON).t(iconId);
		return icon!=null ? icon.getImage() : null;
	}
}
