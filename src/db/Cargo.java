package db;

import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

public class Cargo {

	Manager m;
	Cargo(){
		this.m = new Manager();
	}

	void addMember(Connection con, 
			int id_ladunku,int id_rodzaju,float waga,int id_historii){
		String sql = "INSERT INTO ladunek "+
			"(id_ladunku,id_rodzaju,waga,id_historii)" +
	         "VALUES ("+id_ladunku+", "+id_rodzaju+","+waga+", "+id_historii+"); ";
		m.update(con,sql);
	}
	
	void removeMember(Connection con, int cargoID){
		String sql = "DELETE from ladunek WHERE id_ladunku = "+cargoID+";";
		m.update(con,sql);
	}
	
	DefaultTableModel getTableCargo(Connection con){
		String sql = "SELECT * FROM ladunek;";
		DefaultTableModel table = m.getTable(con, sql); 
		//nie jestem pewna czy sie dobrze tworzy
		//do wykorzystania przez JTable
		return table;
	}
}
