package a.entity.gus.b.entitysrc2.database.cx.initdb.entity_compile_err;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20211003";}

	
	public static final String TABLENAME = "entity_compile_err";

	public static final String COL_ID = 				"id";
	public static final String COL_ENTITYNAME = 		"entityname";
	public static final String COL_FILENAME = 			"filename";
	public static final String COL_DATE = 				"date";
	public static final String COL_LINE = 				"line";
	public static final String COL_LINENB = 			"linenb";
	public static final String COL_LINEPOS = 			"linepos";
	public static final String COL_TYPE = 				"type";
	public static final String COL_DESCRIPTION =		"description";

	public static final String DEF_ID = 				"BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL";
	public static final String DEF_ENTITYNAME = 		"VARCHAR(200) NOT NULL";
	public static final String DEF_FILENAME = 			"VARCHAR(50) NOT NULL";
	public static final String DEF_DATE = 				"DATETIME NOT NULL";
	public static final String DEF_LINE = 				"VARCHAR(200) NOT NULL";
	public static final String DEF_LINENB = 			"INT NOT NULL";
	public static final String DEF_LINEPOS = 			"INT NOT NULL";
	public static final String DEF_TYPE = 				"VARCHAR(50) NOT NULL";
	public static final String DEF_DESCRIPTION = 		"VARCHAR(200) NOT NULL";
	
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "CREATE TABLE "+TABLENAME+" ("
				+COL_ID+" "+			DEF_ID+", "
				+COL_ENTITYNAME+" "+	DEF_ENTITYNAME+", "
				+COL_FILENAME+" "+		DEF_FILENAME+", "
				+COL_DATE+" "+			DEF_DATE+", "
				+COL_LINE+" "+			DEF_LINE+", "
				+COL_LINENB+" "+		DEF_LINENB+", "
				+COL_LINEPOS+" "+		DEF_LINEPOS+", "
				+COL_TYPE+" "+			DEF_TYPE+", "
				+COL_DESCRIPTION+" "+	DEF_DESCRIPTION+")";
		
		execute(cx, sql);
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
