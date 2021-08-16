package a.entity.gus.b.swing1.dialog.popup1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowStateListener;
import java.awt.Toolkit;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;


public class EntityImpl extends S1 implements Entity, E, P, V {

	public String creationDate() {return "20210816";}

	public static final int DEFAULT_WIDTH = 400;
	
	
	private JFrame mainFrame;
	private Dialog1 dialog;



	public EntityImpl() throws Exception
	{
		mainFrame = (JFrame) Outside.resource(this,"mainframe");
		dialog = new Dialog1();
	}
	
	
	public void e() throws Exception
	{dialog.hideContent();}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) dialog.hideContent();
		else dialog.showContent((JComponent) obj,DEFAULT_WIDTH);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(obj==null) dialog.hideContent();
		else dialog.showContent((JComponent) obj,int_(key));
	}
	
	
	private void popupHidden()
	{send(this,"popupHidden()");}
	
	
	
	private int int_(String s)
	{return Integer.parseInt(s);}
	
	
	
	
	private class Dialog1 extends JDialog implements AWTEventListener, WindowStateListener
	{
		public Dialog1()
		{
			super(mainFrame,false);
			setUndecorated(true);
			setResizable(false);
		}
		
		public void showContent(JComponent content, int w)
		{
			setContentPane(content);
			pack();
			setSize(w,getHeight());
			setLocationRelativeTo(null);
			
			if(isVisible()) return;
			
			Toolkit.getDefaultToolkit().addAWTEventListener(this,AWTEvent.MOUSE_EVENT_MASK);
			mainFrame.addWindowStateListener(this);
			setVisible(true);
		}
		
		
	
		public void eventDispatched(AWTEvent event)
		{if(event.getID()==501) hideContent();}
		
		
		public void windowStateChanged(WindowEvent e)
		{hideContent();}
		
		
		
		public void hideContent()
		{
			if(!isVisible()) return;
			
			Toolkit.getDefaultToolkit().removeAWTEventListener(this);
			mainFrame.removeWindowStateListener(this);
			setVisible(false);
			popupHidden();
		}
	}
}
