package a.entity.gus.b.entitysrc2.gui.javaeditor.custcomp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210926";}

	public static final Font FONT = new Font("Courier",Font.PLAIN,14);
	public static final Insets MARGIN = new Insets(0,5,0,5);
	public static final Color SELECTION = Color.BLACK;
	public static final Color SELECTED = Color.WHITE;


	private Service undoRedo;
	private Service paintCaretLine;
	private Service slashComment;
	private Service autoEdit1;
	
	public EntityImpl() throws Exception
	{
		undoRedo = Outside.service(this,"gus.b.swing1.textcomp.cust.action.ctrl_zy.undoredo");
		paintCaretLine = Outside.service(this,"gus.b.swing1.textarea1.p.paint.caretline");
		slashComment = Outside.service(this,"gus.b.swing1.textcomp.cust.action.ctrl_shift_slash.comment");
		autoEdit1 = Outside.service(this,"gus.b.swing1.textcomp.cust.autoedit1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextArea comp = (JTextArea) obj;
		
		comp.setMargin(MARGIN);
		comp.setFont(FONT);
		comp.setSelectionColor(SELECTION);
		comp.setSelectedTextColor(SELECTED);
		
		paintCaretLine.p(comp);
		undoRedo.p(comp);
		slashComment.p(comp);
		autoEdit1.p(comp);
	}
}
