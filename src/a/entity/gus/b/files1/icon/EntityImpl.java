package a.entity.gus.b.files1.icon;

import java.io.File;

import javax.swing.Icon;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210812";}

	public static final String DIR = "#";

	
	private Service iconOs;
	private Service buildIcon;
	private Service getExt;
	
	
	
	public EntityImpl() throws Exception
	{
		iconOs = Outside.service(this,"gus.a.file.icon.os");
		buildIcon = Outside.service(this,"gus.b.icons1.builder");
		getExt = Outside.service(this,"gus.a.file.getext");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		File file = (File) obj;
		
		String iconId = iconId(file);
		Icon icon = (Icon) buildIcon.t(iconId);
		if(icon!=null) return icon;
		
		return iconOs.t(file);
	}
	
	
	private String iconId(File file) throws Exception {
		if(file.isDirectory()) return "dir";
		String ext = (String) getExt.t(file);
		return "FILE_" + ext;
	}
}