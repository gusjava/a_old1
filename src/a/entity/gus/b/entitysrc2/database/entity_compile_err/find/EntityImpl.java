package a.entity.gus.b.entitysrc2.database.entity_compile_err.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
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
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		
		String sql = "SELECT * FROM "+TABLENAME+" WHERE "+COL_ENTITYNAME+"=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();
		
		Set data = new HashSet();
		while(rs.next())
		{
			Map m = new HashMap();
			transfer(m,rs,COL_ENTITYNAME);
			transfer(m,rs,COL_FILENAME);
			transfer(m,rs,COL_DATE);
			transfer(m,rs,COL_LINE);
			transfer(m,rs,COL_LINENB);
			transfer(m,rs,COL_LINEPOS);
			transfer(m,rs,COL_TYPE);
			transfer(m,rs,COL_DESCRIPTION);
			data.add(m);
		}
		st.close();
		return data;
	}
	
	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
