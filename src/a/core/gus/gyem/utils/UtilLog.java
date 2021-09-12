package a.core.gus.gyem.utils;

import java.util.Date;

import a.core.gus.gyem.GyemVersion;

public class UtilLog {

	public static void println(String m) {
		System.out.println(GyemVersion.CORE_ID+"\t"+UtilDate.hhmmss(new Date())+"\t"+m);
	}
}
