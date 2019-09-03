package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBAccessHelper {
	private static DBAccessHelper dao;
	public static final String DRIVER_MYSQ = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://24.47.137.77:3306/CSE305_PROJ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String name = "victor";
	public static final String pass = "victor";

	//loading Driver
	private DBAccessHelper() {
		try {
			Class.forName(DRIVER_MYSQ);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DBAccessHelper getDAO() {
		if (dao == null)
			dao = new DBAccessHelper();
		return dao;
	}
	//connect to the database
	public Connection getConnection() {
		Connection conn=null;
		try {
			conn = (Connection) DriverManager.getConnection(url, name, pass);
			//System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//execute() with not return
	public void execute(String sqlString, Connection c) {
		Connection conn = c;
		try {
			PreparedStatement stmt = (PreparedStatement)conn.prepareStatement(sqlString);
			stmt.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// excuteQuary with return
	public ResultSet executeQuery(String sqlString, Connection c) {
		ResultSet rs = null;
		Connection conn = c;
		try {
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sqlString);
			rs=stmt.executeQuery();        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
