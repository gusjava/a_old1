package a.entity.gus.b.entitysrc2.database.entity_resource.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210906";}

	public static final String TABLENAME = "entity_resource";
	
	public static final String COL_NAME = "name";
	public static final String COL_CALL = "call";

	public static final String KEY_NAME = "name";
	public static final String KEY_RESOURCES = "resources";
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String entityName = (String) data.get(KEY_NAME);
		List list = (List) data.get(KEY_RESOURCES);
		
		for(int i=0;i<list.size();i++)
		{
			String call = (String) list.get(i);
			String sql = "INSERT INTO "+TABLENAME+" ("+COL_NAME+","+COL_CALL+") VALUES (?,?) ";
			executeUpdate(cx, sql, entityName, call);
		}
	}
	
	
	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for(int i=0;i<params.length;i++) st.setObject(i+1,params[i]);
		st.executeUpdate();
		st.close();
	}
}
