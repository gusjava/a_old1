package a.core.gus.gyem;

public class GyemConst {

	/*
	 * MAIN
	 */
	
	public static final String MAIN_LAUNCH_DATE = "launch.date";
	public static final String MAIN_LAUNCH_ARGS = "launch.args";
	public static final String MAIN_CORE_NAME = "core.name";
	public static final String MAIN_CORE_BUILD = "core.build";
	
	/*
	 * PARAM
	 */
	
	public static final String PARAM_CONFIG = "config";

	/*
	 * CONFIGPATH
	 */

	public static final String CONFIGPATH_ROOT = "/a/config/gus/gyem/";
	public static final String CONFIGPATH_PARAM = "/a/config/gus/gyem/param";
	
	/*
	 * CONFIGLOC
	 */
	
	public static final String CONFIGLOC_PROP = "prop";
	public static final String CONFIGLOC_MAPPING = "mapping";
	public static final String CONFIGLOC_ICON = "icon/";
	
	/*
	 * MODULECLASS
	 */
    
	public static final String MODULECLASS_START	= "a.core.gus.gyem.";
	public static final String MODULECLASS_END		= ".Module";
    
	/*
	 * ENTITYCLASS
	 */
    
	public static final String ENTITYCLASS_START	= "a.entity.";
	public static final String ENTITYCLASS_END		= ".EntityImpl";
	
	/*
	 * PROP
	 */
	
	public static final String PROP_BEFORE			= "before";
	public static final String PROP_AFTER			= "after";
	public static final String PROP_APP_MAINGUI		= "app.maingui";
	public static final String PROP_APP_MAINGUI_DISABLED = "app.maingui.disabled";
	public static final String PROP_APP_TITLE		= "app.title";
	public static final String PROP_APP_ICON		= "app.icon";
	public static final String PROP_APP_SIZE		= "app.size";
	
	public static final String DEFAULTPROP_APP_TITLE	= "Application";
	public static final String DEFAULTPROP_APP_SIZE		= "600 400";
	
	/*
	 * RB
	 */
	
	public static final String RB_DEFAULT			= "entity";
	
	/*
	 * MODULES
	 */
	
	public static final Class M001_E_CUSTOMIZE = 				a.core.gus.gyem.m001.e.customize.Module.class;
	public static final Class M002_E_LAUNCH = 					a.core.gus.gyem.m002.e.launch.Module.class;
	public static final Class M003_G_PROP = 					a.core.gus.gyem.m003.g.prop.Module.class;
	public static final Class M004_G_PROP_CONFIG = 				a.core.gus.gyem.m004.g.prop.config.Module.class;
	public static final Class M005_T_CONFIG_LOC_LOAD_PROP =		a.core.gus.gyem.m005.t.config.loc.load.prop.Module.class;
	public static final Class M006_T_CONFIG_PATH_LOAD_PROP = 	a.core.gus.gyem.m006.t.config.path.load.prop.Module.class;
	public static final Class M007_T_CONFIG_PATH_INPUTSTREAM = 	a.core.gus.gyem.m007.t.config.path.inputstream.Module.class;
	public static final Class M008_T_CONFIG_LOC_TO_PATH = 		a.core.gus.gyem.m008.t.config.loc.to.path.Module.class;
	public static final Class M009_G_CONFIG_ID = 				a.core.gus.gyem.m009.g.config.id.Module.class;
	public static final Class M010_G_PARAM = 					a.core.gus.gyem.m010.g.param.Module.class;
	public static final Class M011_G_PARAM_OUTSIDE =			a.core.gus.gyem.m011.g.param.outside.Module.class;
	public static final Class M012_G_PARAM_INSIDE =				a.core.gus.gyem.m012.g.param.inside.Module.class;
	public static final Class M013_T_MODULE_BUILD = 			a.core.gus.gyem.m013.t.module.build.Module.class;
	public static final Class M014_G_SERVICE_BUILD_EMPTY = 		a.core.gus.gyem.m014.g.service.build.empty.Module.class;
	public static final Class M015_T_ENTITY_PROVIDE = 			a.core.gus.gyem.m015.t.entity.provide.Module.class;
	public static final Class M016_T_ENTITY_UNIQUE = 			a.core.gus.gyem.m016.t.entity.unique.Module.class;
	public static final Class M017_T_ENTITY_GENERATE = 			a.core.gus.gyem.m017.t.entity.generate.Module.class;
	public static final Class M018_T_ENTITY_FINDCLASS = 		a.core.gus.gyem.m018.t.entity.findclass.Module.class;
	public static final Class M019_T_ENTITY_LOADCLASS = 		a.core.gus.gyem.m019.t.entity.loadclass.Module.class;
	public static final Class M020_T_ENTITY_NAMETOPATH = 		a.core.gus.gyem.m020.t.entity.nametopath.Module.class;
	public static final Class M021_P_EXECUTE_SEQUENCE = 		a.core.gus.gyem.m021.p.execute.sequence.Module.class;
	public static final Class M022_F_EXECUTE_RULE = 			a.core.gus.gyem.m022.f.execute.rule.Module.class;
	public static final Class M023_E_MAINFRAME = 				a.core.gus.gyem.m023.e.mainframe.Module.class;
	public static final Class M024_G_MAINFRAME_FIND = 			a.core.gus.gyem.m024.g.mainframe.find.Module.class;
	public static final Class M025_E_MAINFRAME_EXIT = 			a.core.gus.gyem.m025.e.mainframe.exit.Module.class;
	public static final Class M026_P_MAINFRAME_HANDLE = 		a.core.gus.gyem.m026.p.mainframe.handle.Module.class;
	public static final Class M027_G_MAINFRAME_CONTENT = 		a.core.gus.gyem.m027.g.mainframe.content.Module.class;
	public static final Class M028_G_BUILDGUI_ENTITY = 			a.core.gus.gyem.m028.g.buildgui.entity.Module.class;
	public static final Class M029_G_BUILDGUI_DEFAULTPANEL = 	a.core.gus.gyem.m029.g.buildgui.defaultpanel.Module.class;
	public static final Class M030_T_BUILDGUI_ERRORPANEL = 		a.core.gus.gyem.m030.t.buildgui.errorpanel.Module.class;
	public static final Class M031_F_PROP_BOOL_DF = 			a.core.gus.gyem.m031.f.prop.bool.df.Module.class;
	public static final Class M032_F_PROP_BOOL_DT = 			a.core.gus.gyem.m032.f.prop.bool.dt.Module.class;
	public static final Class M033_T_CONFIG_LOAD_ICON =			a.core.gus.gyem.m033.t.config.load.icon.Module.class;
	public static final Class M034_T_CONFIG_PATH_URL = 			a.core.gus.gyem.m034.t.config.path.url.Module.class;
	public static final Class M035_T_MANAGER_SERVICE = 			a.core.gus.gyem.m035.t.manager.service.Module.class;
	public static final Class M036_T_MANAGER_RESOURCE = 		a.core.gus.gyem.m036.t.manager.resource.Module.class;
	public static final Class M037_P_MANAGER_ERR = 				a.core.gus.gyem.m037.p.manager.err.Module.class;
	public static final Class M038_T_RESOURCE_FINDRULE = 		a.core.gus.gyem.m038.t.resource.findrule.Module.class;
	public static final Class M039_T_RESOURCE_FINDRULE1 = 		a.core.gus.gyem.m039.t.resource.findrule1.Module.class;
	public static final Class M040_T_RESOURCE_PROVIDE = 		a.core.gus.gyem.m040.t.resource.provide.Module.class;
	public static final Class M041_T_RESOURCE_BUILD = 			a.core.gus.gyem.m041.t.resource.build.Module.class;
	public static final Class M042_T_RESOURCE_FIND_RB = 		a.core.gus.gyem.m042.t.resource.find.rb.Module.class;
	public static final Class M043_T_RB_ENTITY = 				a.core.gus.gyem.m043.t.rb.entity.Module.class;
	public static final Class M044_T_ENTITY_FINDNAME = 			a.core.gus.gyem.m044.t.entity.findname.Module.class;
	public static final Class M045_T_SERVICE_WRAP = 			a.core.gus.gyem.m045.t.service.wrap.Module.class;
	public static final Class M046_T_SERVICE_CALL_DESCRIPTION = a.core.gus.gyem.m046.t.service.call.description.Module.class;
	public static final Class M047_G_MAPPING = 					a.core.gus.gyem.m047.g.mapping.Module.class;
	public static final Class M048_G_MAPPING_CONFIG = 			a.core.gus.gyem.m048.g.mapping.config.Module.class;
	public static final Class M049_G_ERR_LIST = 				a.core.gus.gyem.m049.g.err.list.Module.class;
}
