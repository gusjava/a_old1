package a.entity.gus.b.appli1.execute.about.panel;

import a.framework.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Map;
import javax.swing.border.Border;
import java.text.SimpleDateFormat;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20210816";}


	public static final long DAY_TO_MILLI = 86400000L;	
	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	
	public static final Border EMPTY8888 = BorderFactory.createEmptyBorder(8,8,8,8);
	public static final Color BACKGROUND = Color.WHITE;
	public static final Color FOREGROUND = new Color(0,102,204);
	
	public static final int SIZE1 = 22;
	public static final int SIZE2 = 18;
	public static final int SIZE3 = 14;
	public static final int SIZE4 = 10;
	
	public static final String MESSAGE_CREATEDBY = "Application créée par ";
	public static final String MESSAGE_INCOMPLETE = "Version non finalisée!";
	public static final String MESSAGE_UNDEFINED = "indéfini";
	
	

//	private Service labelWebApp;
//	private Service labelWebGus06;
//	private Service labelMail;
//	private Service getCopyright;
	
	private Map prop;
	private Icon icon_a;
	private Icon icon_app;
	private String coreBuild;
	private String coreName;

	private JPanel panel;
	
	
	
	public EntityImpl() throws Exception
	{
//		labelWebApp = Outside.service(this,"*gus.app.info.appli.website.label");
//		labelWebGus06 = Outside.service(this,"*gus.app.info.framework.website.label");
//		labelMail = Outside.service(this,"*gus.app.info.author.email.label");
//		getCopyright = Outside.service(this,"gus.app.info.framework.copyright");
		
		icon_a = (Icon) Outside.resource(this,"icon#framework_a");
		icon_app = (Icon) Outside.resource(this,"icon#app");
		coreBuild = (String) Outside.resource(this,"core.build");
		coreName = (String) Outside.resource(this,"core.name");
		prop = (Map) Outside.resource(this,"prop");
		
		panel = new AboutPanel();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
//	private String copyright() throws Exception
//	{return (String) getCopyright.g();}
	
	
		
	private String prop(String key, String defaultValue)
	{
		if(prop.containsKey(key)) return (String) prop.get(key);
		return defaultValue;
	}
	
	private String prop(String key)
	{return prop(key,"?");}
	
	
	
	private String line1()
	{return prop("app.title");}
	
	private String line2()
	{return "version "+prop("app.version");}
	
	private String line3()
	{return MESSAGE_CREATEDBY+prop("app.author.name");}
	
	private String appName()
	{return "app name: "+prop("jar.buildid");}
	
	private String appBuild()
	{return "app build: "+prop("jar.buildtime");}
	
	private String coreId()
	{return "core ID: "+coreName+"|"+coreBuild;}
	
	private String appAge()
	{return "app age: "+age1()+" ("+age2()+")";}
	
	private String age1()
	{return age_str(prop("app.firstbuild"));}
				
	private long age2()
	{return age(prop("jar.buildtime"));}
	
	private boolean isNotFinalized()
	{return prop("app.version").endsWith("*");}
	
	
	
	
	private Font font(int size)
	{return new Font("Calibri",Font.PLAIN,size);}
	
	private Font fontBold(int size)
	{return new Font("Calibri",Font.BOLD,size);}
	
	
	private JLabel label(int size, Color color, JComponent comp)
	{
		if(comp==null) return null;
		JLabel label = (JLabel) comp;
		label.setFont(font(size));
		label.setForeground(color);
		return label;
	}
		
	private JLabel label(int size, Color color, String text)
	{
		JLabel label = new JLabel(text);
		label.setFont(font(size));
		label.setForeground(color);
		return label;
	}
		
	private JLabel labelBold(int size, Color color, String text)
	{
		JLabel label = new JLabel(text);
		label.setFont(fontBold(size));
		label.setForeground(color);
		return label;
	}
		
	private JLabel labelEmpty()
	{
		JLabel label = new JLabel(" ");
		label.setFont(font(9));
		return label;
	}
	
	
	
	private Color findColor()
	{
		String fg = prop("color.aboutforeground",null);
		if(fg!=null) return new Color(Integer.parseInt(fg));
		return FOREGROUND;
	}
	
	private String age_str(String timeStamp)
	{
		long age = age(timeStamp);
		if(age==-1) return MESSAGE_UNDEFINED;
		return age>1?age+" jours":age+" jour";
	}
		
	private long age(String timeStamp)
	{
		try{return daysFrom(YYYYMMDD.parse(timeStamp));}
		catch(Exception e){return -1;}
	}
		
		
	
	
	private long timeFrom(Date d)
	{return System.currentTimeMillis()-d.getTime();}
	
	private long daysFrom(Date d)
	{return timeFrom(d)/DAY_TO_MILLI;}
	
	
	
	
	
	
	private class AboutPanel extends JPanel
	{
		public AboutPanel() throws Exception
		{
			super(new GridBagLayout());
			
			Color color = findColor();
	
			put();
			put(labelBold(SIZE1,color,line1()));
			put(labelBold(SIZE2,color,line2()));
			if(isNotFinalized()) put(labelBold(SIZE2,Color.RED,MESSAGE_INCOMPLETE));
			
			put();
			put(new JLabel(icon_app));
			put();
			put(label(SIZE3,color,line3()));
//			put(label(SIZE3,color,(JComponent) labelMail.i()));
//			put(label(SIZE3,color,(JComponent) labelWebApp.i()));
			put();
			
			put();
			put(label(SIZE4,Color.BLACK,appName()));
			put(label(SIZE4,Color.BLACK,coreId()));
			put(label(SIZE4,Color.BLACK,appBuild()));
			put(label(SIZE4,Color.BLACK,appAge()));
			put();
			
			put(labelEmpty());
			put(new JLabel(icon_a));
			put(labelEmpty());
//			put(label(SIZE4,Color.LIGHT_GRAY,copyright()));
//			put(label(SIZE4,Color.LIGHT_GRAY,(JComponent) labelWebGus06.i()));
			put(labelEmpty());
			
			setOpaque(true);
			setBorder(EMPTY8888);
			setBackground(BACKGROUND);
		}
		
		
		
		
		int n=0;
		
		private void put()
		{put(new JLabel(" "));}
		
		private void put(JComponent c)
		{
			if(c==null) return;
			add(c,nextConstraints());
		}
		
		private GridBagConstraints nextConstraints()
		{return createConstraints(1,n++);}
		
		private GridBagConstraints createConstraints(int x, int y)
		{
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = x;
			c.gridy = y;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.anchor = GridBagConstraints.CENTER;
			c.weightx = 0.0;
			c.weighty = 0.0;
			return c;
		}
	}
}
