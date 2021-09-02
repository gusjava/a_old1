package a.entity.gus.a.jdbc.resultset.tostringlist;

import java.sql.ResultSet;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210902";}

	public Object t(Object obj) throws Exception
	{
		ResultSet rs = (ResultSet) obj;
		ArrayList list = new ArrayList();
		while(rs.next()) list.add(rs.getString(1));
		rs.close();
		return list;
	}
}
