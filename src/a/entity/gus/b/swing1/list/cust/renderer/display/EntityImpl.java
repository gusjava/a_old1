package a.entity.gus.b.swing1.list.cust.renderer.display;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210822";}


	public static final Color COLOR_SELECT = new Color(244,244,244);
	public static final Color COLOR_UNSELECT = Color.WHITE;
	
	private Service labelDisplay;
	
	public EntityImpl() throws Exception
	{
		labelDisplay = Outside.service(this,"gus.b.swing1.label.cust2.display");
	}

	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		list.setCellRenderer(new ListRenderer0());
	}
	
	
	private class ListRenderer0 extends JLabel implements ListCellRenderer
	{
		public ListRenderer0()
		{
			super();
			setOpaque(true);
			setBackground(COLOR_UNSELECT);
		}
		
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setBackground(getBackground(isSelected));
			paintLabel(this,(String) value);
			return this;
		}
		
		public Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
	}
	
	
	private void paintLabel(JLabel label, String display) {
		try {labelDisplay.v(display, label);}
		catch (Exception e) {Outside.err(this,"paintLabel(JLabel,String)",e);}
	}
}