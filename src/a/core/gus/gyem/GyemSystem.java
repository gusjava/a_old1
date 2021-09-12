package a.core.gus.gyem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.utils.UtilArgs;
import a.core.gus.gyem.utils.UtilDate;
import a.framework.E;
import a.framework.F;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.T;

public class GyemSystem extends GyemConst {
	
	/*
	 * INIT
	 */

	protected static void init(String[] args) throws Exception {
		main = new HashMap();

		put(MAIN_LAUNCH_DATE, new Date());
		put(MAIN_LAUNCH_ARGS, args);
		put(MAIN_LAUNCH_CLASS, GyemMain.class);
		put(MAIN_CORE_NAME, GyemVersion.CORE_NAME);
		put(MAIN_CORE_BUILD, GyemVersion.CORE_BUILD);
		
		Field[] ff = GyemConst.class.getFields();
		for(int i=0;i<ff.length;i++) {
			if(ff[i].getType().equals(Class.class)) {
				initModule((Class) ff[i].get(null));
			}
		}
		Outside.setManager(new GyemManager());
	}
	
	/*
	 * MAIN
	 */
	
	protected static Map main;
	
	protected static void put(String key, Object value) {
		main.put(key, value);
	}
	
	protected static boolean has(String key) {
		return main.containsKey(key);
	}
	
	protected static Object get(String key) {
		return has(key) ? main.get(key) : null;
	}
	
	/*
	 * MODULE
	 */
	
	private static void initModule(Class c) throws Exception {
		put(classToName(c), c.getDeclaredConstructor().newInstance());
	}
	
	protected static Object module(Class c) {
		return get(classToName(c));
	}
	
	private static String classToName(Class c) {
		String n = c.getName();
		return n.substring(MODULECLASS_START.length(), n.length() - MODULECLASS_END.length());
	}
	
	protected static E moduleE(Class c) {
		return (E) module(c);
	}
	
	protected static G moduleG(Class c) {
		return (G) module(c);
	}
	
	protected static T moduleT(Class c) {
		return (T) module(c);
	}
	
	protected static P moduleP(Class c) {
		return (P) module(c);
	}
	
	protected static F moduleF(Class c) {
		return (F) module(c);
	}

	/*
	 * FATAL
	 */
	
	protected static void fatalMain(Exception e) {
		fatal(e, 1, GyemMain.class);
	}
	
	protected static void fatalManagerErr(Exception e) {
		fatal(e, 2, GyemManager.class);
	}
	
	private static void fatal(Exception ex, int code, Class c) {
		String message = "A fatal error has occured in " + c.getSimpleName();
		
		System.err.println(message);
		ex.printStackTrace();
		
		String abortTime = abortTime();
		String startTime = startTime();
		String argsLine = argsLine();
		String coreName = GyemVersion.CORE_NAME;
		String coreBuild = GyemVersion.CORE_BUILD;
		
		File dir = new File("fatalerr");
		dir.mkdirs();
		
		String fileName = "fatalerr_" + abortTime + "_" + coreName.replace(".", "_") + "_" + coreBuild + ".txt";
		File file = new File(dir, fileName);
		
		try(PrintStream p = new PrintStream(file)) {

			p.println("FATAL ERROR REPORT");
			p.println("------------------");
			p.println(message);
			p.println("The application has aborted");
			p.println("------------------");
			p.println("- Abort time: " + abortTime);
			p.println("- Launch time: " + startTime);
			p.println("- Launch args: " + argsLine);
			p.println("- Launch dir: " + System.getProperty("user.dir"));
			p.println("- Java version: " + System.getProperty("java.version"));
			p.println("- Java home: " + System.getProperty("java.home"));
			p.println("- Core name: " + coreName);
			p.println("- Core build: " + coreBuild);
			p.println("- Exit code: " + code);
			p.println("- Exception type: " + ex.getClass().getName());
			p.println("- Exception message: " + ex.getMessage());
			p.println("------------------");
			ex.printStackTrace(p);
			
			p.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.exit(code);
	}
	
	
	private static String abortTime() {
		return UtilDate.yyyymmdd_hhmmss(new Date());
	}
	
	private static String startTime() {
		if(!has(MAIN_LAUNCH_DATE)) return "not found";
		Object obj = get(MAIN_LAUNCH_DATE);
		if(!(obj instanceof Date)) return "Invalid type: "+obj.getClass().getName();
		return UtilDate.yyyymmdd_hhmmss((Date) obj);
	}
	
	private static String argsLine() {
		if(!has(MAIN_LAUNCH_ARGS)) return "not found";
		Object obj = get(MAIN_LAUNCH_ARGS);
		if(!(obj instanceof String[])) return "Invalid type: "+obj.getClass().getName();
		return UtilArgs.toString((String[]) obj);
	}
}
