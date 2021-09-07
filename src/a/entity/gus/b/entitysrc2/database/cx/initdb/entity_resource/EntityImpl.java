package a.entity.gus.b.entitysrc2.database.cx.initdb.entity_resource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210906";}

	public static final String TABLENAME = "entity_resource";

	public static final String COL_NAME = 				"name";
	public static final String COL_CALL = 				"call";

	public static final String DEF_NAME = 				"VARCHAR(200) NOT NULL";
	public static final String DEF_CALL = 				"VARCHAR(500) NOT NULL";
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_NAME+" "+		DEF_NAME+", "
				+COL_CALL+" "+		DEF_CALL+", "
				+"PRIMARY KEY ("+COL_NAME+","+COL_CALL+"))";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
