package a.core.gus.gyem.m030.t.buildgui.errorpanel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.core.gus.gyem.GyemSystem;
import a.core.gus.gyem.utils.UtilThrowable;
import a.framework.T;

public class Module extends GyemSystem implements T {

	public Object t(Object obj) throws Exception {
		Exception ex = (Exception) obj;
		String s = UtilThrowable.description(ex);
		JTextArea area = new JTextArea(s);
		
		area.setEditable(false);
		area.setForeground(Color.RED);
		area.setBackground(Color.BLACK);
		area.setFont(area.getFont().deriveFont((float) 14));
		
		return new JScrollPane(area);
	}
}
