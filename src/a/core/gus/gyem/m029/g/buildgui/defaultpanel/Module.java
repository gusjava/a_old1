package a.core.gus.gyem.m029.g.buildgui.defaultpanel;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.JPanel;

import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.GyemVersion;
import a.framework.G;

public class Module extends GyemSystem implements G {

	public Object g() throws Exception {

		JPanel panel = new JPanel(new BorderLayout());
		JEditorPane area = new JEditorPane();
		area.setText(buildText());

		area.setEditable(false);
		area.setBackground(panel.getBackground());
		area.setMargin(new Insets(8,8,8,8));
		area.setFont(area.getFont().deriveFont((float) 20));
		
		panel.add(area, BorderLayout.CENTER);
		return panel;
	}
	
	private String buildText() throws Exception {
		StringBuffer b = new StringBuffer();
		
		Map prop = (Map) moduleG(M003_G_PROP).g();
		Map prop0 = (Map) moduleG(M004_G_PROP_CONFIG).g();
		String config = (String) moduleG(M009_G_CONFIG_ID).g();
		
		b.append("core name = "+GyemVersion.CORE_NAME+"\n");
		b.append("core build = "+GyemVersion.CORE_BUILD+"\n");
		b.append("app config = "+config+"\n\n");
		
		if(prop.isEmpty()) {
			b.append("Properties not found.\n");
		}
		else {
			b.append(PROP_APP_MAINGUI+" not found inside properties.\n");
		}
		if(config==null) {
			b.append("You should specify a config for this application.\n");
		}
		
		return b.toString();
	}
}
