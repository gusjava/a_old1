package a.entity.gus.b.entitysrc2.database.cx.initdb.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210906";}

	
	public static final String TABLENAME = "entity";

	public static final String COL_NAME = 				"name";
	public static final String COL_FEATURES = 			"features";
	public static final String COL_CREATIONDATE = 		"creationdate";
	public static final String COL_LENGTH = 			"length";
	public static final String COL_CALLNB = 			"callnb";
	public static final String COL_FILENB = 			"filenb";

	public static final String DEF_NAME = 				"VARCHAR(200) PRIMARY KEY NOT NULL";
	public static final String DEF_FEATURES = 			"VARCHAR(11) NOT NULL";
	public static final String DEF_CREATIONDATE = 		"DATETIME NOT NULL";
	public static final String DEF_LENGTH = 			"INT NOT NULL";
	public static final String DEF_CALLNB = 			"INT NOT NULL";
	public static final String DEF_FILENB = 			"INT NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_NAME+" "+			DEF_NAME+", "
				+COL_FEATURES+" "+		DEF_FEATURES+", "
				+COL_CREATIONDATE+" "+	DEF_CREATIONDATE+", "
				+COL_LENGTH+" "+		DEF_LENGTH+", "
				+COL_CALLNB+" "+		DEF_CALLNB+", "
				+COL_FILENB+" "+		DEF_FILENB+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
