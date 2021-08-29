package a.entity.gus.b.entitysrc2.database.cx.main;

import java.io.File;
import java.sql.Connection;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210829";}
	
	public static final String DBNAME = "entity_src";
	public static final String USER = "admin";
	public static final String PWD = "admin";

	
	private Service buildCx;
	
	private File dbDir;
	private File file;
	private Connection cx;

	public EntityImpl() throws Exception {
		buildCx = Outside.service(this,"gus.b.api2.h2.cx.build");
		dbDir = (File) Outside.resource(this,"path#path.databasedir");
		file = new File(dbDir, DBNAME);
	}
	
	public Object g() throws Exception {
		if(cx==null || cx.isClosed()) init();
		return cx;
	}
	
	private void init() throws Exception {
		cx = (Connection) buildCx.t(new Object[] {file, USER, PWD});
	}
}
