package a.core.gus.gyem.m029.g.buildgui.defaultpanel;

import java.awt.BorderLayout;
import java.awt.Insets;

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
		area.setMargin(new Insets(5,5,5,5));
		area.setFont(area.getFont().deriveFont((float)20));
		
		
		panel.add(area,BorderLayout.CENTER);
		return panel;
	}
	
	private String buildText() {
		StringBuffer b = new StringBuffer();
		
		b.append(PROP_APP_MAINGUI+" not found inside application properties\n");
		b.append("core name = "+GyemVersion.CORE_NAME+"\n");
		b.append("core build = "+GyemVersion.CORE_BUILD+"\n");
		
		return b.toString();
	}
}
