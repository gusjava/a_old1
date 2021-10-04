package a.entity.gus.b.entitysrc2.database.entity_compile_err.deleteall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20211004";}

	public static final String TABLENAME = "entity_compile_err";
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String sql = "DELETE FROM "+TABLENAME;
		executeUpdate(cx, sql);
	}
	
	
	private void executeUpdate(Connection cx, String sql) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		st.executeUpdate();
		st.close();
	}
}
