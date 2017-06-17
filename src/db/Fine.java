package db;

import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

public class Fine{
	
	Manager m;
	Fine(){
		this.m = new Manager();
	}

	void addMember(Connection con, int id_kierowcy, String data,
			float oplata, int pkt_karne, int id_historii){
		String sql = "INSERT INTO mandat "+
			"(id_kierowcy,data,oplata,pkt_karne,id_historii)"
	        + "VALUES ("+id_kierowcy+", "+data+", "+oplata+", "
				+pkt_karne+","+id_historii+" );";
		m.update(con,sql);
	}
	
	void removeMember(Connection con, int fineID){
		String sql = "DELETE from mandat WHERE id_mandatu = "+fineID+";";
		m.update(con,sql);
	}
	
	DefaultTableModel getTableFine(Connection con){
		String sql = "SELECT * FROM mandat;";
		DefaultTableModel table = m.getTable(con, sql); 
		//nie jestem pewna czy sie dobrze tworzy
		//do wykorzystania przez JTable
		return table;
	}
}
