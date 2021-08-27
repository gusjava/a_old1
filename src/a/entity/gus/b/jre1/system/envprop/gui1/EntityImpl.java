package a.entity.gus.a.jre.system.envprop.gui1;

import a.framework.*;
import javax.swing.JComponent;
import java.util.*;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20210825";}

	private JPanel panel;

	public EntityImpl() throws Exception
	{
		Map m1 = System.getProperties();
		Map m2 = System.getenv();
    	
		JScrollPane p1 = new JScrollPane(new JTableMap(m1));
		setBorder(p1,"System properties  ("+m1.size()+" values)");
    	
		JScrollPane p2 = new JScrollPane(new JTableMap(m2));
		setBorder(p2,"System env  ("+m2.size()+" values)");
    	
		panel = new JPanel(new GridLayout(0,1));
		panel.add(p1);
		panel.add(p2);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private void setBorder(JComponent p, String title)
	{
 		TitledBorder border = BorderFactory.createTitledBorder(title);
		p.setBorder(border);	
	}
	
	
	private String[][] content(Map map)
	{
		String[][] content = new String[map.size()][2];
        		ArrayList keys = new ArrayList(map.keySet());
 		Collections.sort(keys);
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			String value = (String) map.get(key);
			content[i][0]=key;
			content[i][1]=value;
		}
		return content;
	}

	
	
	private class JTableMap extends JTable
	{
		public JTableMap(Map map)
		{
			super(content(map),new String[]{"key","value"});
			getTableHeader().setReorderingAllowed(false);
		}
	}
}
