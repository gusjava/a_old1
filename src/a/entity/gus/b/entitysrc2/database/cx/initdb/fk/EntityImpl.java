package a.entity.gus.b.entitysrc2.database.cx.initdb.fk;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210906";}
	
	public void p(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		{
			String sql = "ALTER TABLE entity_service " + 
					"ADD FOREIGN KEY (name) " + 
					"REFERENCES entity(name)";
			execute(cx, sql);
		}
		
		{
			String sql = "ALTER TABLE entity_resource " + 
					"ADD FOREIGN KEY (name) " + 
					"REFERENCES entity(name)";
			execute(cx, sql);
		}
		
		{
			String sql = "ALTER TABLE entity_link " + 
					"ADD FOREIGN KEY (name1) " + 
					"REFERENCES entity(name)";
			execute(cx, sql);
		}
		
		{
			String sql = "ALTER TABLE entity_link " + 
					"ADD FOREIGN KEY (name2) " + 
					"REFERENCES entity(name)";
			execute(cx, sql);
		}
	}
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
}
