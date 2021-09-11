package a.entity.gus.b.swing1.textarea1.p.paint.caretline;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.text.JTextComponent;

import a.framework.Entity;
import a.framework.P;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210911";}

	public static final Color COLOR = new Color(250,250,204);

	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof P)
		((P) obj).p(new Painter());
	}


	private class Painter implements P
	{
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

			Graphics g = (Graphics) o[0];
			JTextComponent comp = (JTextComponent) o[1];

			Rectangle2D rect = comp.modelToView2D(comp.getCaretPosition());
			if(rect==null) return;

			g.setColor(COLOR);
			g.fillRect(0,(int) rect.getY(),comp.getWidth(), (int) rect.getHeight());
		}
	}
}