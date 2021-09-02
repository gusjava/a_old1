package a.entity.gus.b.h2cache1.check;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210902";}
	
	public static final String TABLENAME_INITIALIZED = "initialized";
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Object init = o[1];
		check(cx, init);
	}
	
	
	private void check(Connection cx, Object init) throws Exception {
		String sql1 = "CREATE TABLE IF NOT EXISTS "+TABLENAME_INITIALIZED+" (date DATETIME)";
		execute(cx, sql1);
		
		String sql2 = "SELECT date FROM "+TABLENAME_INITIALIZED;
		Date date = (Date) queryOne(cx, sql2);
		
		if(date==null) {
			System.out.println("Initialize database'structure");
			((P) init).p(cx);
			
			String sql3 = "INSERT INTO "+TABLENAME_INITIALIZED+" (date) VALUES ('"+formatDate(new Date())+"')";
			execute(cx, sql3);
			return;
		}
		
		String lastUpdate = (String) ((G) init).g();
		Date date0 = parseDate(lastUpdate);
		if(date0.after(date)) {
			System.out.println("Reset database'structure");

			String sql4 = "DROP ALL OBJECTS";
			execute(cx, sql4);
			check(cx, init);
			return;
		}
		
		System.out.println("Database'structure is up to date");
		
	}
	
	
	
	private void execute(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		st.execute(sql);
		st.close();
	}
	
	private Object queryOne(Connection cx, String sql) throws SQLException {
		Statement st = cx.createStatement();
		ResultSet rs = st.executeQuery(sql);
		Object result = rs.next() ? rs.getObject(1) : null;
		st.close();
		return result;
	}
	
	/*
	 * DATE
	 */
	
	private String formatDate(Date value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(value);
	}
	
	private Date parseDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(s);
	}
}
