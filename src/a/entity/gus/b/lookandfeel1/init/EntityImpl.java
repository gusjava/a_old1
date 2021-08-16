package a.entity.gus.b.lookandfeel1.init;

import a.framework.*;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20210812";}

	private Service handle;

	public EntityImpl() throws Exception
	{
		handle = Outside.service(this,"gus.b.lookandfeel1.handle");
	}
	
	public void e() throws Exception
	{
		handle.v("Button.font",		"font#SansSerif plain 12");
		handle.v("CheckBox.font",	"font#SansSerif plain 14");
		handle.v("Label.font",		"font#SansSerif plain 12");
		handle.v("Menu.font",		"font#SansSerif plain 12");
		handle.v("MenuItem.font",	"font#SansSerif plain 12");
		handle.v("List.font",		"font#SansSerif plain 12");
		handle.v("RadioButton.font",		"font#SansSerif plain 14");
		handle.v("RadioButtonMenuItem.font",	"font#SansSerif plain 12");
		handle.v("TabbedPane.font",		"font#SansSerif plain 12");
		handle.v("TitledBorder.font",	"font#SansSerif bold 16");
		handle.v("ToggleButton.font",	"font#SansSerif plain 12");
		handle.v("ToolTip.font",		"font#SansSerif plain 13");
		handle.v("ToolTip.border",		"border#lineborder gray");
		handle.v("ToolTip.background",	"color#WHITE");
	}
}