package a.entity.gus.a.throwable.build.description;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import a.core.gus.gyem.GyemConst;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210826";}

	
	
	public Object t(Object obj) throws Exception {
		return description((Throwable) obj);
	}
	
	
	private String description(Throwable e) {
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
	
	private String message(Throwable e) {
		if (e.toString().startsWith("java.lang.Exception"))
			return e.getMessage();
		return e.toString();
	}

	private String location(Throwable e) {
		StackTraceElement[] ste_ = e.getStackTrace();
		if (ste_.length == 0)
			return null;
		int n = 0;
		StackTraceElement ste = ste_[0];
		while (ste.getFileName() == null && ste_.length > n + 1)
			ste = e.getStackTrace()[++n];
		return location(ste);
	}

	private String location(StackTraceElement ste) {
		if (ste == null) return "null";
		return ste.getClassName() + "@" + ste.getMethodName() + " (" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
	}
	
	private String problem(Throwable e) {
		return location(firstEntitySTE(firstCause(e)));
	}

	private StackTraceElement firstEntitySTE(Throwable e) {
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++)
			if (ste[i].getClassName().startsWith(GyemConst.ENTITYCLASS_START))
				return ste[i];
		return null;
	}
	
	private void printStackTrace(Throwable e, PrintStream p) {
		p.println("STACK TRACE:");
		e.printStackTrace(p);
	}
	
	private Throwable firstCause(Throwable e) {
		while (e.getCause() != null)
			e = e.getCause();
		return e;
	}
}
