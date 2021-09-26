package a.entity.gus.b.entitysrc2.database.entity.findall.asmap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}

	public static final String TABLENAME = "entity";

	public static final String COL_NAME = "name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATIONDATE = "creationdate";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALLNB = "callnb";
	public static final String COL_FILENB = "filenb";


	public EntityImpl() throws Exception {
		
	}
	
	
	public Object t(Object obj) throws Exception {
		Connection cx = (Connection) obj;
		
		String sql = "SELECT * FROM "+TABLENAME;
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		Map data = new HashMap();
		while(rs.next()) {
			Map m = new HashMap();
			transfer(m,rs,COL_NAME);
			transfer(m,rs,COL_FEATURES);
			transfer(m,rs,COL_CREATIONDATE);
			transfer(m,rs,COL_LENGTH);
			transfer(m,rs,COL_CALLNB);
			transfer(m,rs,COL_FILENB);
			
			String name = (String) m.get(COL_NAME);
			data.put(name,m);
		}
		return data;
	}
	
	private void transfer(Map m, ResultSet rs, String key) throws SQLException {
		m.put(key, rs.getObject(key));
	}
}
