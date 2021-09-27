package a.entity.gus.b.entitysrc2.engine.main;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;
import a.framework.V;

public class EntityImpl extends S1 implements Entity, G, R, V, E, F {
	public String creationDate() {return "20210829";}
	
	public static final String PERSIST_KEY = EntityImpl.class.getName()+"_last";

	
	private Service engine;
	private Service persist;
	private Service getConnection;
	private Service getRootDir;
	private String devId;
	
	private Object selected;
	private Map map;
	

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus.b.entitysrc2.engine");
		persist = Outside.service(this,"gus.b.persist1.main");
		getConnection = Outside.service(this,"gus.b.entitysrc2.database.cx.main");
		getRootDir = Outside.service(this,"gus.b.entitysrc1.rootdir");
		devId = (String) Outside.resource(this, "param#dev");
		
		if(devId==null) throw new Exception("dev not found inside params");
	}
	
	
	public void e() throws Exception
	{
		load();
	}
	
	
	public Object g() throws Exception
	{
		if(map==null) load();
		return map;
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("rootDir")) return getRootDir.g();
		if(key.equals("devId")) return devId;
		if(key.equals("cx")) return getConnection.g();
		if(key.equals("selected")) return selected;
		
		if(key.equals("keys")) return new String[] {"rootDir","devId","cx","selected"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {selectEntity(obj);return;}
		throw new Exception("Unknown key: "+key);
	}

	
	

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String permission = (String) o[0];
		if(permission.equals("canDeleteEntity")) return canDeleteEntity((String) o[1]);
		if(permission.equals("canRenameEntity")) return canRenameEntity((String) o[1]);
		if(permission.equals("canDuplicateEntity")) return canDuplicateEntity((String) o[1]);
		if(permission.equals("canModifyEntity")) return canModifyEntity((String) o[1]);
		
		throw new Exception("Unknown permission: "+permission);
	}
	
	
	
	

	
	
	
	
	
	private void load() throws Exception {
		File rootDir = (File) getRootDir.g();
		Connection cx = (Connection) getConnection.g();
		Long lastTime = findLastTime();
		
		map = (Map) engine.t(new Object[] {rootDir, cx, lastTime});
		changed();
	}
	
	
	private long findLastTime() throws Exception {
		String str = (String) persist.r(PERSIST_KEY);
		long time = str!=null ? Long.parseLong(str) : 0L;
		persist.v(PERSIST_KEY, ""+System.currentTimeMillis());
		return time;
	}
	
	
	
	
	
	
	private boolean canDeleteEntity(String entityName) {
		return isMyEntity(entityName);
	}
	
	private boolean canRenameEntity(String entityName) {
		return isMyEntity(entityName);
	}
	
	private boolean canDuplicateEntity(String entityName) {
		return true;
	}
	
	private boolean canModifyEntity(String entityName) {
		return isMyEntity(entityName);
	}
	
	private boolean isMyEntity(String entityName) {
		return entityName!=null && entityName.startsWith(devId+".");
	}
	
	
	
	
	
	private void selectEntity(Object selected)
	{
		this.selected = selected;
		selected();
	}
	
	
	
	

	
	
	
	
	private void changed() {
		send(this,"changed()");
	}
	
	private void selected() {
		send(this,"selected()");
	}
}
