package a.entity.gus.b.entitysrc2.engine.main;

import java.io.File;
import java.sql.Connection;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210829";}
	
	public static final String PERSIST_KEY = EntityImpl.class.getName()+"_last";

	
	private Service engine;
	private Service persist;
	private Service getConnection;
	private Service getRootDir;

	public EntityImpl() throws Exception {
		engine = Outside.service(this,"gus.b.entitysrc2.engine");
		persist = Outside.service(this,"gus.b.persist1.main");
		getConnection = Outside.service(this,"gus.b.entitysrc2.database.cx.main");
		getRootDir = Outside.service(this,"gus.b.entitysrc1.rootdir");
	}
	
	
	public Object g() throws Exception {
		File rootDir = (File) getRootDir.g();
		Connection cx = (Connection) getConnection.g();
		Long lastTime = findLastTime();
		
		return engine.t(new Object[] {rootDir, cx, lastTime});
	}
	
	private long findLastTime() throws Exception {
		String str = (String) persist.r(PERSIST_KEY);
		long time = str!=null ? Long.parseLong(str) : 0L;
		persist.v(PERSIST_KEY, ""+System.currentTimeMillis());
		return time;
	}
}
