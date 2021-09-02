package a.entity.gus.b.entitysrc2.database.cx.initdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.Entity;
import a.framework.G;
import a.framework.P;

public class EntityImpl implements Entity, P, G {
	public String creationDate() {return "20210901";}
	
	public static final String TABLENAME_ENTITY = "entity";
	public static final String TABLENAME_ENTITYREL = "entityrel";

	public static final String STRUCT_LAST_UPDATE = "2021-09-02 08:16:00"; //yyyy-MM-dd HH:mm:ss
	
	

	public EntityImpl() throws Exception {
		
	}
	


	public Object g() throws Exception {
		return STRUCT_LAST_UPDATE;
	}
	
	
	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		
		String sql1 = "CREATE TABLE "+TABLENAME_ENTITY+" (name VARCHAR(200), features VARCHAR(11), creationdate DATETIME)";
		execute(cx, sql1);
	}
	
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}

}
