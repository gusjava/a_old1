package a.core.gus.gyem.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import a.core.gus.gyem.GyemConst;

public class UtilThrowable {

	public static String message(Throwable e) {
		if (e.toString().startsWith("java.lang.Exception"))
			return e.getMessage();
		return e.toString();
	}

	public static String location(Throwable e) {
		StackTraceElement[] ste_ = e.getStackTrace();
		if (ste_.length == 0)
			return null;
		int n = 0;
		StackTraceElement ste = ste_[0];
		while (ste.getFileName() == null && ste_.length > n + 1)
			ste = e.getStackTrace()[++n];
		return location(ste);
	}

	public static String location(StackTraceElement ste) {
		if (ste == null)
			return "null";
		return ste.getClassName() + "@" + ste.getMethodName() + " (" + ste.getFileName() + ":" + ste.getLineNumber()
				+ ")";
	}

	public static String stacktraceToString(Throwable e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(baos);
		printStackTrace(e, p);
		p.close();
		return baos.toString();
	}

	public static String description(Throwable e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(baos);
		p.println("ERROR:");
		p.println(message(e));
		p.println();
		p.println("LOCATION:");
		p.println(location(e));
		p.println();
		p.println("PROBLEM:");
		p.println(problem(e));
		p.println();
		printStackTrace(e, p);
		p.close();
		return baos.toString();
	}

	public static String problem(Throwable e) {
		return location(firstEntitySTE(firstCause(e)));
	}

	public static StackTraceElement firstEntitySTE(Throwable e) {
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++)
			if (ste[i].getClassName().startsWith(GyemConst.ENTITYCLASS_START))
				return ste[i];
		return null;
	}

	public static Throwable firstCause(Throwable e) {
		while (e.getCause() != null)
			e = e.getCause();
		return e;
	}

	public static void printCauses(Throwable e, PrintStream p) {
		p.println("CAUSES:");
		p.println("caused by: " + message(e) + " at " + location(e));
		while (e.getCause() != null) {
			e = e.getCause();
			p.println("caused by: " + message(e) + " at " + location(e));
		}
	}

	public static void printStackTrace(Throwable e, PrintStream p) {
		p.println("STACK TRACE:");
		e.printStackTrace(p);
	}
}
