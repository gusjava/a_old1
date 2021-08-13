package a.entity.gus.a.java.jdk.versionmapping;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}


	public Object t(Object obj) throws Exception
	{
		int majorVersion = Integer.parseInt(""+obj);
    	
		switch(majorVersion) {
			case 61:return "J2SE 17";
			case 60:return "J2SE 16";
			case 59:return "J2SE 15";
			case 58:return "J2SE 14";
			case 57:return "J2SE 13";
			case 56:return "J2SE 12";
			case 55:return "J2SE 11";
			case 54:return "J2SE 10";
			case 53:return "J2SE 9";
			case 52:return "J2SE 8";
			case 51:return "J2SE 7";
			case 50:return "J2SE 6";
			case 49:return "J2SE 5";
			case 48:return "JDK 1.4";
			case 47:return "JDK 1.3";
			case 46:return "JDK 1.2";
			case 45:return "JDK 1.1";
			default:throw new Exception("Unsupported major version: "+majorVersion);
		}
	}
}