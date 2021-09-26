package a.entity.gus.b.swing1.textcomp.cust.autoedit1.tab_inv.perform;

import a.framework.*;

import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210926";}

	
	private Service trimStart;

	public EntityImpl() throws Exception
	{
		trimStart = Outside.service(this,"gus.a.transform.string.trim.start");
	}
	
	
	public void p(Object obj) throws Exception
	{
		final JTextComponent comp = (JTextComponent) obj;
		
		String selection = comp.getSelectedText();
		if(selection==null) return;
		
		selection = widdenSelection(selection,comp);
		final String s0 = moveLeft(selection);
		
		SwingUtilities.invokeLater(()->replaceText(comp,s0));
	}
	
	
	private String moveLeft(String s) throws Exception
	{
		String[] line = s.split("\n");
	
		boolean moved = false;
		for(int i=0;i<line.length;i++)
		{
			if(line[i].startsWith("\t")) 
			{
				line[i] = line[i].substring(1);
				moved = true;
			}
		}
		
		if(!moved)
		{
			for(int i=0;i<line.length;i++)
			line[i] = (String) trimStart.t(line[i]);
		}
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<line.length;i++)
		b.append(line[i]+"\n");
		
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
	
	
	
	private String widdenSelection(String selection, JTextComponent comp)
	{
		if(selection.startsWith("\t")) 	return selection;
		
		int start = comp.getSelectionStart();
		if(start==0) return selection;
		
		char c = comp.getText().charAt(start-1);
		if(c=='\t') 
		{
			int end = comp.getSelectionEnd();
			comp.select(start-1,end);
			return "\t"+selection;
		}
		
		int k=0;
		StringBuffer buff = new StringBuffer();
		while((c==' ' || c=='\t') && start-1-k>0)
		{
			k++;
			buff.append(' ');
			c = comp.getText().charAt(start-1-k);
		}
		if(k>0)
		{
			int end = comp.getSelectionEnd();
			comp.select(start-k,end);
			
			buff.append(selection);
			return buff.toString();
		}
		return selection;
	}
}
