package a.core.gus.gyem.m024.g.mainframe.find;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {
	
	private JFrame frame;

	public Object g() throws Exception {
		if(moduleF(M031_F_PROP_BOOL_DF).f(PROP_APP_MAINGUI_DISABLED)) return null;
		
		if(frame==null) init();
		return frame;
	}
	
	
	private void init() throws Exception {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {exit();}
		});
	}
	
	private void exit() {
		try{moduleE(M025_E_MAINFRAME_EXIT).e();}
		catch (Exception e) {System.exit(1);}
	}
}
