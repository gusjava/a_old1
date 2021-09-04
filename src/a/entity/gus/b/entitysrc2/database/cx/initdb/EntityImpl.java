package a.entity.gus.b.entitysrc2.database.cx.initdb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.Entity;
import a.framework.G;
import a.framework.P;

public class EntityImpl implements Entity, P, G {
	public String creationDate() {return "20210901";}
	
	/*
	 * TABLE entity
	 */
	
	public static final String TABLENAME_ENTITY = "entity";

	public static final String COL_NAME = 				"name";
	public static final String COL_FEATURES = 			"features";
	public static final String COL_CREATIONDATE = 		"creationdate";
	public static final String COL_LENGTH = 			"length";
	public static final String COL_CALLNB = 			"callnb";

	public static final String DEF_NAME = 				"VARCHAR(200) PRIMARY KEY NOT NULL";
	public static final String DEF_FEATURES = 			"VARCHAR(11) NOT NULL";
	public static final String DEF_CREATIONDATE = 		"DATETIME NOT NULL";
	public static final String DEF_LENGTH = 			"INT NOT NULL";
	public static final String DEF_CALLNB = 			"INT NOT NULL";
	
	/*
	 * TABLE entity_service
	 */
	
	public static final String TABLENAME_ENTITY_SERVICE = "entity_service";
	
	/*
	 * TABLE entity_resource
	 */
	
	public static final String TABLENAME_ENTITY_RESOURCE = "entity_resource";

	

	public static final String STRUCT_LAST_UPDATE = "2021-09-04 21:36:00"; //yyyy-MM-dd HH:mm:ss
	public static final boolean ALWAYS_RESET = false;
	

	public EntityImpl() throws Exception {
		
	}
	


	public Object g() throws Exception {
		if(ALWAYS_RESET) return null;
		return STRUCT_LAST_UPDATE;
	}
	
	
	public void p(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		
		String sql1 = "CREATE TABLE "+TABLENAME_ENTITY+" ("
				+ COL_NAME + " "+ 				DEF_NAME +", "
				+ COL_FEATURES + " " + 			DEF_FEATURES + ", "
				+ COL_CREATIONDATE + " " + 		DEF_CREATIONDATE + ", "
				+ COL_LENGTH + " " + 			DEF_LENGTH + ", "
				+ COL_CALLNB + " " + 			DEF_CALLNB + ")";
		execute(cx, sql1);
	}
	
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}

}
