package a.entity.gus.b.entitysrc2.database.entity_link.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210913";}

	public static final String TABLENAME = "entity_link";
	
	public static final String COL_NAME1 = "name1";
	public static final String COL_NAME2 = "name2";

	public static final String KEY_NAME = "name";
	public static final String KEY_LINKS = "links";
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];

		String entityName = (String) data.get(KEY_NAME);
		List list = new ArrayList((Set) data.get(KEY_LINKS));
		
		for(int i=0;i<list.size();i++)
		{
			String linked = (String) list.get(i);
			
			try
			{
				String sql = "INSERT INTO "+TABLENAME+" ("+COL_NAME1+","+COL_NAME2+") VALUES (?,?) ";
				executeUpdate(cx, sql, entityName, linked);
			}
			catch(SQLException e)
			{
				throw new Exception("Failed add link ["+linked+"] to entity ["+entityName+"]", e);
			}
		}
	}
	
	
	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for(int i=0;i<params.length;i++) st.setObject(i+1,params[i]);
		st.executeUpdate();
		st.close();
	}
}
