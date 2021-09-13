package a.entity.gus.b.entitysrc2.database.cx.initdb.entity_link;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210913";}

	public static final String TABLENAME = "entity_link";

	public static final String COL_NAME1 = 				"name1";
	public static final String COL_NAME2 = 				"name2";

	public static final String DEF_NAME1 = 				"VARCHAR(200) NOT NULL";
	public static final String DEF_NAME2 = 				"VARCHAR(200) NOT NULL";
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_NAME1+" "+		DEF_NAME1+", "
				+COL_NAME2+" "+		DEF_NAME2+", "
				+"PRIMARY KEY ("+COL_NAME1+","+COL_NAME2+"))";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
