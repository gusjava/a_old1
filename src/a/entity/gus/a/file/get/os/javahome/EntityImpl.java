package a.entity.gus.a.file.get.os.javahome;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210810";}

	public Object g() throws Exception {
		return new File(System.getProperty("java.home"));
	}
}
