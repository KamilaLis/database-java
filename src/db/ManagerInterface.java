package db;

import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

public interface ManagerInterface {
	
	Connection connect(String host, String database, String user, String password);
	void closeConnection(Connection con);
	void update(Connection con,String sql);
	String[] ask(Connection con, String query, String[] names);
	DefaultTableModel getTable(Connection con, String sql);
}
