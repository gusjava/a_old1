package a.core.gus.gyem.utils;

public class UtilArgs {

	public static String toString(String[] args) {
		if(args==null || args.length==0) return "";
		StringBuilder b = new StringBuilder(args[0]);
        for(int i=1;i<args.length;i++) b.append(" "+args[i]);
		return b.toString();
	}
}
