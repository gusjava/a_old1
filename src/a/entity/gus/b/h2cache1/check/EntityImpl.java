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
	
	public static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
	
	
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
			initialize(cx,init);
			return;
		}
		Date date0 = parseDate((String) ((G) init).g());
		if(date0==null || date0.after(date)) {
			reset(cx,init);
			return;
		}
		System.out.println("Database'structure is up to date");
	}
	
	
	
	private void initialize(Connection cx, Object init) throws Exception {
		System.out.println("Database'structure not found");
		System.out.println("Initializing structure...");
		((P) init).p(cx);
		
		String sql1 = "INSERT INTO "+TABLENAME_INITIALIZED+" (date) VALUES ('"+formatDate(new Date())+"')";
		execute(cx, sql1);
	}
	
	
	private void reset(Connection cx, Object init) throws Exception {
		System.out.println("Database'structure is out dated");
		System.out.println("Resetting structure...");

		String sql1 = "DROP ALL OBJECTS";
		execute(cx, sql1);
		
		String sql2 = "CREATE TABLE "+TABLENAME_INITIALIZED+" (date DATETIME)";
		execute(cx, sql2);
		
		((P) init).p(cx);
		
		String sql3 = "INSERT INTO "+TABLENAME_INITIALIZED+" (date) VALUES ('"+formatDate(new Date())+"')";
		execute(cx, sql3);
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
		return new SimpleDateFormat(FORMAT_DATE).format(value);
	}
	
	private Date parseDate(String s) throws ParseException {
		if(s==null) return null;
		return new SimpleDateFormat(FORMAT_DATE).parse(s);
	}
}
