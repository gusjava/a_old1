package a.entity.gus.a.swing.table.cust.renderer.mapstring1;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210827";}
	
	public static final Color COLOR_SELECT = new Color(244,244,244);
	public static final Color COLOR_UNSELECT = Color.WHITE;

	
	
	public void p(Object obj) throws Exception {
		JTable table = (JTable) obj;
		table.setDefaultRenderer(String.class, new TableCellRenderer1());
	}
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			if(column==0) setText((String) value);
			else setText(format((String) value));
			
			setBackground(getBackground(isSelected));
			return this;
		}
		
		public Color getBackground(boolean isSelected)
		{return isSelected ? COLOR_SELECT : COLOR_UNSELECT;}
	}
	
	
	private String format(String s)
	{
		StringBuffer b = new StringBuffer();
		b.append("<html>");
		int len = s.length();
		for(int i=0; i<len; i++)
		{
			char c = s.charAt(i);
			String c1 = format(c);
			if(c1!=null) b.append(c1);
			else b.append(c);
		}
		b.append("</html>");
		return b.toString();
	}
	
	private String format(char c) {
		if(c=='\r') return "<b>\\r</b>";
		if(c=='\t') return "<b>\\t</b>";
		if(c=='\n') return "<b>\\n</b>";
		return null;
	}
}
