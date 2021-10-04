package a.entity.gus.b.entitysrc2.database.entity_compile_err.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20211004";}

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
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
		
		Object entityName = data.get(COL_ENTITYNAME);
		Object fileName = data.get(COL_FILENAME);
		Object date = data.get(COL_DATE);
		Object line = data.get(COL_LINE);
		Object lineNb = data.get(COL_LINENB);
		Object linePos = data.get(COL_LINEPOS);
		Object type = data.get(COL_TYPE);
		Object description = data.get(COL_DESCRIPTION);
		
		String sql = "INSERT INTO "+TABLENAME+" ("
				+COL_ENTITYNAME+","
				+COL_FILENAME+","
				+COL_DATE+","
				+COL_LINE+","
				+COL_LINENB+","
				+COL_LINEPOS+","
				+COL_TYPE+","
				+COL_DESCRIPTION+") VALUES (?,?,?,?,?,?,?,?)";
		
		executeUpdate(cx, sql, entityName, fileName, date, line, lineNb, linePos, type, description);
	}
	
	
	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for(int i=0;i<params.length;i++) st.setObject(i+1,params[i]);
		st.executeUpdate();
		st.close();
	}
}
