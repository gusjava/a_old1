package a.entity.gus.b.entitysrc2.database.entity_link.delete2.in;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210913";}

	public static final String TABLENAME = "entity_link";

	public static final String COL_NAME2 = "name2";
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Set names = (Set) o[1];
		
		StringBuffer b =  new StringBuffer();
		b.append("DELETE FROM ");
		b.append(TABLENAME);
		b.append(" WHERE ");
		b.append(COL_NAME2);
		b.append(" IN (");

		int nb = names.size();
		for(int i=0;i<nb;i++) {
			b.append("?");
			if(i<nb-1) b.append(",");
		}
		b.append(")");
		
		executeUpdate(cx, b.toString(), new ArrayList(names));
	}
	
	
	private void executeUpdate(Connection cx, String sql, List params) throws SQLException {
		PreparedStatement st = cx.prepareStatement(sql);
		for(int i=0;i<params.size();i++) st.setObject(i+1,params.get(i));
		st.executeUpdate();
		st.close();
	}
}
