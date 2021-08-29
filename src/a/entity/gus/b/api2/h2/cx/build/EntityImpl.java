package a.entity.gus.b.api2.h2.cx.build;

import java.io.File;
import java.sql.DriverManager;

import org.h2.jdbcx.JdbcDataSource;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210829";}
	
	
	
	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String user = (String) o[1];
		String pwd = (String) o[1];
		
		String url = "jdbc:h2:file:"+file;
		file.getParentFile().mkdirs();
		
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(url);
		dataSource.setUser(user);
		dataSource.setPassword(pwd);
		
		return dataSource.getConnection();
	}
}
