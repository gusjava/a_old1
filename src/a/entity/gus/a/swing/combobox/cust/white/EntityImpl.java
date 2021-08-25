package a.entity.gus.a.swing.combobox.cust.white;

import a.framework.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210825";}

	
	public void p(Object obj) throws Exception
	{init((JComboBox) obj);}
	
	
	private void init(JComboBox combo)
	{
		combo.setRenderer(new Renderer());
		combo.setFont(combo.getFont().deriveFont(Font.PLAIN));
		combo.setBackground(Color.WHITE);
	}


	private class Renderer extends JLabel implements ListCellRenderer
	{
		public Renderer()
		{
			setOpaque(true);
			setFont(getFont().deriveFont(Font.PLAIN));
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setText(getDisplay(value));
			setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
			return this;
		}
		
		private String getDisplay(Object value)
		{
			if(value==null) return " ";
			String s = value.toString();
			if(s.trim().equals("")) return " ";
			return s;
		}
	}
}
