package a.entity.gus.b.swing1.textcomp.cust.action.ctrl_shift_slash.comment.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210926";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		Element root = document.getDefaultRootElement();
		
		int startLine = root.getElementIndex(comp.getSelectionStart());
		int endLine = root.getElementIndex(comp.getSelectionEnd());
		
		Element element1 = document.getParagraphElement(comp.getSelectionStart());
		Element element2 = document.getParagraphElement(comp.getSelectionEnd());
		
		int start = element1.getStartOffset();
		int end = element2.getEndOffset();
		if(end>length) end = length;
		
		String text = comp.getText(start,end-start);
		String text1 = text.startsWith("//") ? decomment(text) : comment(text);
		
		document.remove(start,end-start);
		document.insertString(start,text1,null);
		comp.select(start,start+text1.length()-1);
	}
	
	
	private String decomment(String s)
	{
		StringBuffer b = new StringBuffer();
		boolean start = true;
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c=='/')
			{
				if(!start) b.append(c);
			}
			else if(c=='\n')
			{
				b.append(c);
				start = true;
			}
			else
			{
				b.append(c);
				start = false;
			}
		}
		return b.toString();
	}
	
	
	
	private String comment(String s)
	{
		StringBuffer b = new StringBuffer();
		int n = 0;
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c=='/')
			{
				if(n==0 || n==1)
				{
					n++;
					b.append(c);
				}
				else if(n==2)
				{
					
				}
				else b.append(c);
			}
			else if(c=='\n')
			{
				if(n==0) b.append("//");
				b.append(c);
				n = 0;
			}
			else
			{
				if(n==0) b.append("//");
				b.append(c);
				n = -1;
			}
		}
		return b.toString();
	}
}
