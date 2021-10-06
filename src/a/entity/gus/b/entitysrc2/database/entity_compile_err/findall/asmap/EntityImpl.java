package a.entity.gus.b.entitysrc2.database.entity_compile_err.findall.asmap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20211006";}

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
		Connection cx = (Connection) obj;
		
		String sql = "SELECT * FROM "+TABLENAME+" ORDER BY "+
				COL_ENTITYNAME+","+COL_FILENAME+","+COL_LINENB+","+COL_LINEPOS;
		
		PreparedStatement st = cx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		Map data = new HashMap();
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
			
			String name = (String) m.get(COL_ENTITYNAME);
			
			if(!data.containsKey(name)) data.put(name, new ArrayList());
			((List) data.get(name)).add(m);
		}
		st.close();
		return data;
	}
	
	private void transfer(Map m, ResultSet rs, String key) throws SQLException
	{m.put(key, rs.getObject(key));}
}
