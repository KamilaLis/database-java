package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Manager implements ManagerInterface{

	/*void createTable(){}
	void addMember(){}
	void removeMember(){}*/
	
	public Connection connect(String host, String database, String user, String password){
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection
					("jdbc:postgresql://"+host+":5432/"+database,user,password);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()
					+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return con;
	}
	
	public void closeConnection(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()
					+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Connection with database closed successfully");
	}
	
	
	
	public void update(Connection con,String sql){
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
	}
	
	public String[] ask(Connection con, String query, String[] names){
		Statement stmt = null;
		String[] o = new String[names.length];
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while ( rs.next() ) {
				for (int i=0; i<names.length;++i){
					o[i] = rs.getString(names[i]);
					System.out.println(o[i]);
				}
			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		return o;
	}
	
	public DefaultTableModel getTable(Connection con, String sql){
		Statement stmt = null;
		DefaultTableModel model = new DefaultTableModel();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNumber=rsmd.getColumnCount();
			while ( rs.next() ) {
				Object[] row = new Object[columnNumber];
				for (int col=1; col<columnNumber+1; ++col){
					row[col - 1] = rs.getString(col); // Or even rs.getObject()
				}
				model.addRow(row);
			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return model;
	}
	
/*	void createTable(Connection con){
		String sql = "CREATE TABLE COMPANY " +
				"(ID INT PRIMARY KEY     NOT NULL," +
				" NAME           TEXT    NOT NULL, " +
				" AGE            INT     NOT NULL, " +
				" ADDRESS        CHAR(50), " +
				" SALARY         REAL)";
		update(con,sql);
		System.out.println("Table created successfully");
	}*/
	
}