package a.entity.gus.b.entitysrc2.database.entity_compile_err.distinctnames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20211004";}

	public static final String TABLENAME = "entity_compile_err";
	
	public static final String COL_ENTITYNAME = "entityname";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		
		String sql = "SELECT DISTINCT("+COL_ENTITYNAME+") FROM "+TABLENAME;
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();
		
		Set data = new HashSet();
		while(rs.next()) data.add(rs.getObject(1));
		
		st.close();
		return data;
	}
}
