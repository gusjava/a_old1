package a.entity.gus.b.entitysrc2.database.entity_service.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210907";}

	public static final String TABLENAME = "entity_service";
	
	public static final String COL_NAME = "name";
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		
		String sql = "DELETE FROM "+TABLENAME+" WHERE "+COL_NAME+"=?";
		executeUpdate(cx, sql, entityName);
	}
	
	
	private void executeUpdate(Connection cx, String sql, Object... params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for(int i=0;i<params.length;i++) st.setObject(i+1, params[i]);
		st.executeUpdate();
		st.close();
	}
}
