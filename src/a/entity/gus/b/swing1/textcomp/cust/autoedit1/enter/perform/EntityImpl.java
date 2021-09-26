package a.entity.gus.b.swing1.textcomp.cust.autoedit1.enter.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210926";}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;

		int pos = comp.getCaretPosition();
		if(pos==0) throw new Exception("Invalid caret position after key pressed: 0");
		
		String text = comp.getText();
		
		String text0 = text.substring(0,pos);
		String text1 = text.substring(pos);
		
		int length0 = text0.length();
		
		char lastChar = text0.charAt(length0-1);
		if(lastChar!='\n') throw new Exception("Invalid last caracter: "+lastChar);
		
		String text00 = text0.substring(0,length0-1);
		String[] n = text00.split("\n",-1);
		String last = n[n.length-1];
		
		boolean blocJustStarted = endsWith(last,'{');
		boolean blocJustEnded = startsWith(text1,'}');
		
		int nb = findTabs(last);
		
		StringBuffer b = new StringBuffer();
		int pos_ = pos;
		
		addTabs(b,nb);
		pos_ += nb;
		
		if(blocJustStarted)
		{
			addTabs(b,1);
			pos_++;
			
			if(blocJustEnded)
			{
				b.append("\n");
				addTabs(b,nb);
			}
		}
		
		insert(comp,pos,b.toString());
		comp.setCaretPosition(pos_);
	}
	
	
	
	private void insert(JTextComponent comp, int pos, String s) throws Exception
	{comp.getDocument().insertString(pos,s,null);}
	
	
	
	private int findTabs(String line) throws Exception
	{
		int nb = 0;
		while(nb<line.length() && line.charAt(nb)=='\t') nb++;
		return nb;
	}
	
	
	private boolean endsWith(String s, char c)
	{
		if(s.length()==0) return false;
		return s.charAt(s.length()-1)==c;
	}
	
	private boolean startsWith(String s, char c)
	{
		if(s.length()==0) return false;
		return s.charAt(0)==c;
	}
	
	
	
	private void addTabs(StringBuffer b, int nb)
	{
		for(int i=0;i<nb;i++) b.append('\t');
	}
}
