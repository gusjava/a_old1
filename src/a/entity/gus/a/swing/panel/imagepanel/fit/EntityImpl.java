package a.entity.gus.a.swing.panel.imagepanel.fit;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.image.RenderedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.P;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20210813";}


	private ScreenJPanel screen;
	private Object image;
	
	public EntityImpl() throws Exception
	{
		screen = new ScreenJPanel();
	}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public Object i() throws Exception
	{return screen;}
	
	
	public void p(Object obj) throws Exception
	{
		image = obj;
		screen.repaint();
	}
	
	
	
	
	public class ScreenJPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(image==null) return;
        
			if(image instanceof RenderedImage)
				paintRenderedImage((Graphics2D)g,(RenderedImage)image);
			else if(image instanceof Image)
				paintImage((Graphics2D)g,(Image)image);
			else if(image instanceof ImageIcon)
				paintImageIcon((Graphics2D)g,(ImageIcon)image);
			else if(image instanceof G)
				paintG((Graphics2D)g,(G)image);
		}
		
		private void paintRenderedImage(Graphics2D g2, RenderedImage img)
		{
			int imageW = img.getWidth();
			int imageH = img.getHeight();
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
        		double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;
    		
    			if(cx>=cy)
    			{
    				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
    				AffineTransform af = AffineTransform.getTranslateInstance(a+ins.left,ins.top);
    	        		af.scale(cy,cy);
    	       		 	g2.drawRenderedImage(img,af);
			}
			else
    			{
    				int a = (int)((getHeight()-ins.bottom-ins.top-imageH*cx)/2);		
    				AffineTransform af = AffineTransform.getTranslateInstance(ins.left,a+ins.top);
    				af.scale(cx,cx);
				g2.drawRenderedImage(img,af);
			}
		}
		
		
		private void paintImage(Graphics2D g2, Image img)
		{
			int imageW = img.getWidth(null);
			int imageH = img.getHeight(null);
			if(imageH<=0) return;
		
			Insets ins = getInsets();
		
			double cx = (double)(getWidth()-ins.left-ins.right)/(double)imageW;
    			double cy = (double)(getHeight()-ins.bottom-ins.top)/(double)imageH;

			if(cx>=cy)
			{
				int a = (int)((getWidth()-ins.left-ins.right-imageW*cy)/2);
				int dx = (int)(imageW*cy);
				int dy = getHeight()-ins.bottom-ins.top;
				g2.drawImage(img,a+ins.left,ins.top,dx,dy,this);
			}
			else
			{
				int a = (int)((getHeight()-ins.bottom-ins.top-imageH*cx)/2);	
				int dx = getWidth()-ins.left-ins.right;
				int dy = (int)(imageH*cx);
				g2.drawImage(img,ins.left,a+ins.top,dx,dy,this);
			}
		}
		
		
		private void paintImageIcon(Graphics2D g2, ImageIcon img)
		{paintImage(g2,img.getImage());}
		
		
		private void paintG(Graphics2D g2, G g)
		{
			try
			{
				Object img = g.g();
				if(img==null) return;
				
				if(img instanceof RenderedImage)
					paintRenderedImage(g2,(RenderedImage)img);
				else if(img instanceof Image)
					paintImage(g2,(Image)img);
				else if(img instanceof ImageIcon)
					paintImageIcon(g2,(ImageIcon)img);
			}
			catch(Exception e){}
		}
	}
}