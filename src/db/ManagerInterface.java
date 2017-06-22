package db;

import java.sql.Connection;

public interface ManagerInterface {
	
	Connection connect(String host, String database, String user, String password);
	void closeConnection(Connection con);

}
