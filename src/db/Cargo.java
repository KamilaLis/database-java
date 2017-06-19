package db;

import java.sql.Connection;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Cargo extends Table{

	void addMember(Connection con, 
			int id_rodzaju,
			float waga,int id_historii){
		String sql = "INSERT INTO ladunek "+
			"(id_rodzaju,waga,id_historii)" +
	         "VALUES ("+id_rodzaju+","+waga+", "+id_historii+"); ";
		update(con,sql);
	}
	
	void removeMember(Connection con, int cargoID){
		String sql = "DELETE from ladunek WHERE id_ladunku = "+cargoID+";";
		update(con,sql);
	}
	
	DefaultTableModel getTableCargo(Connection con){
		String sql = "SELECT * FROM ladunek;";
		DefaultTableModel table = getTable(con, sql); 
		return table;
	}
	
	List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM ladunek;";
		return getTableAsList(con, sql); 
	}
}
