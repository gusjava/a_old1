package a.entity.gus.b.entitysrc2.database.entity.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210903";}

	public static final String TABLENAME_ENTITY = "entity";

	public static final String COL_NAME = "name";
	public static final String COL_FEATURES = "features";
	public static final String COL_CREATIONDATE = "creationdate";
	public static final String COL_LENGTH = "length";
	public static final String COL_CALLNB = "callnb";

	public static final String KEY_RESOURCES = "resources";
	public static final String KEY_SERVICES = "services";


	public EntityImpl() throws Exception {
		
	}
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
		
		String name = (String) data.get(COL_NAME);
		String features = (String) data.get(COL_FEATURES);
		String creationdate = (String) data.get(COL_CREATIONDATE);
		int length = (int) data.get(COL_LENGTH);
		int callNb = (int) data.get(COL_CALLNB);
		
		String sql = "INSERT INTO "+TABLENAME_ENTITY+" "
				+ "("+COL_NAME+","+COL_FEATURES+","+COL_CREATIONDATE+","+COL_LENGTH+","+COL_CALLNB+") "
				+ "VALUES (?,?,?,?,?) ";
		
		executeUpdate(cx, sql, name, features, creationdate, length, callNb);
		
	}
	
	
	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for(int i=0;i<params.length;i++) st.setObject(i+1,params[i]);
		st.executeUpdate();
		st.close();
	}
}
