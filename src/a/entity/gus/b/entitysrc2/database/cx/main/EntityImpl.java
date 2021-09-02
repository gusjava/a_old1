package a.entity.gus.b.entitysrc2.database.cx.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G, ActionListener {
	public String creationDate() {return "20210829";}
	
	public static final String DBNAME = "cache_entity_src";
	public static final String USER = "admin";
	public static final String PWD = "admin";

	
	private Service buildCx;
	private Service initDb;
	private Service checkDb;
	private Service exit;
	
	private File dbDir;
	private File file;
	private Connection cx;

	public EntityImpl() throws Exception {
		buildCx = Outside.service(this,"gus.b.api2.h2.cx.build");
		initDb = Outside.service(this,"gus.b.entitysrc2.database.cx.initdb");
		checkDb = Outside.service(this,"gus.b.h2cache1.check");
		exit = Outside.service(this,"gus.b.cust1.exit");
		
		dbDir = (File) Outside.resource(this,"path#path.databasedir");
		file = new File(dbDir, DBNAME);
		
		exit.addActionListener(this);
	}
	
	public Object g() throws Exception {
		if(cx==null || cx.isClosed()) init();
		return cx;
	}
	
	private void init() throws Exception {
		cx = (Connection) buildCx.t(new Object[] {file, USER, PWD});
		checkDb.p(new Object[] {cx, initDb});
	}
	
	
	public void actionPerformed(ActionEvent e)
	{closeCx();}
	
	
	private void closeCx() {
		try {if(cx!=null) cx.close();}
		catch(Exception e) {}
	}
}
