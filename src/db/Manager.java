package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Manager implements ManagerInterface{
	
	public Connection connect(String host, String database, String user, String password){
		// Polaczenie z baza danych (parametry podawane przy wywolaniu aplikacji)
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
		// Zakonczenie polaczenia
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
}