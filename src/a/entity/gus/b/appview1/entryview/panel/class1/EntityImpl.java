package a.entity.gus.b.appview1.entryview.panel.class1;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20210813";}


	private Service formPanel;
	private Service jdkMapping;
	private Service analyze;
	private Service setToString;
	
	private JPanel panel;
	private JTextArea area;
	
	private InputStream is;
	
	
	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus.b.swing1.panel.formpanel.string");
		jdkMapping = Outside.service(this,"gus.a.java.jdk.versionmapping");
		analyze = Outside.service(this,"gus.b.api1.jdepend.analyze.data");
		setToString = Outside.service(this,"gus.b.tostring1.set");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) formPanel.i(),BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		is = (InputStream) obj;
		if(is==null) resetGui();
		else updateGui();
	}
	
	
	private void resetGui() throws Exception
	{
		formPanel.e();
		area.setText("");
	}
	
	
	
	private void updateGui() throws Exception
	{
		Map data = (Map) analyze.t(is);
		
		Integer minor = (Integer) data.get("minorversion");
		Integer major = (Integer) data.get("majorversion");
		Set imports = (Set) data.get("imports");
		
		String jdkVer = (String) jdkMapping.t(major);
		String imports_ = (String) setToString.t(imports);
	
		formPanel.e();
		formPanel.v("JDK version",jdkVer);
		formPanel.v("Minor version",""+minor);
		formPanel.v("Major version",""+major);
		formPanel.v("Imports nb",""+imports.size());
		
		area.setText(imports_);
	}
}