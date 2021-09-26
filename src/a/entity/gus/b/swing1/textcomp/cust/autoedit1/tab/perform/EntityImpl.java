package a.entity.gus.b.swing1.textcomp.cust.autoedit1.tab.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210926";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		String selection = (String) o[1];
		
		if(selection==null) return;
		
		String s0 = moveRight(selection);
		replaceText(comp,s0);
	}
	
	
	private String moveRight(String s)
	{
		StringBuffer b = new StringBuffer();
		String[] line = s.split("\n");
		for(int i=0;i<line.length;i++)
		{
			b.append("\t"+line[i]+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	private void replaceText(JTextComponent comp, String s0)
	{
		try
		{
			int start = comp.getSelectionStart();
			comp.replaceSelection(s0);
			comp.select(start,start+s0.length());
		}
		catch(Exception e)
		{Outside.err(this,"replaceText(JTextComponent,String)",e);}
	}
}
