package a.entity.gus.b.entitysrc2.database.entity_link.find2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210920";}

	public static final String TABLENAME = "entity_link";
	
	public static final String COL_NAME1 = "name1";
	public static final String COL_NAME2 = "name2";

	public EntityImpl() throws Exception
	{
		
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		
		String sql = "SELECT "+COL_NAME1+" FROM "+TABLENAME+" WHERE "+COL_NAME2+"=?";
		PreparedStatement st = cx.prepareStatement(sql);
		st.setObject(1, entityName);
		ResultSet rs = st.executeQuery();
		
		Set data = new HashSet();
		while(rs.next())
		{
			String link = (String) rs.getObject(COL_NAME1);
			data.add(link);
		}
		st.close();
		return data;
	}
}
