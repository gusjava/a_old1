package a.entity.gus.a.swing.list.cust.renderer.obj;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210813";}


	public static final Color COLOR_SELECT = new Color(244,244,244);
	public static final Color COLOR_UNSELECT = Color.WHITE;

	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		list.setCellRenderer(new ListRenderer0());
	}
	
	
	private class ListRenderer0 extends JLabel implements ListCellRenderer
	{
		private Font font_p;
		private Font font_i;
		
		public ListRenderer0()
		{
			super();
			setOpaque(true);
			font_p = getFont().deriveFont(Font.PLAIN);
			font_i = getFont().deriveFont(Font.ITALIC);
			
			setBackground(COLOR_UNSELECT);
			setFont(font_p);
		}
		
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setText(getText(value));
			setFont(getFont(value));
			setBackground(getBackground(isSelected));
			return this;
		}
		
		public String getText(Object value)
		{return value!=null ? value.toString() : "null";}
		
		public Font getFont(Object value)
		{return value!=null ? font_p : font_i;}
		
		public Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
	}
}