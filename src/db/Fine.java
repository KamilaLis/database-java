package db;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Fine extends Table{

	static void addMember(Connection con, int id_kierowcy,
			Date data,
			float oplata, int pkt_karne, int id_historii){
		String sql = "INSERT INTO mandat "+
			"(id_kierowcy,data,oplata,pkt_karne,id_historii)"
	        + "VALUES ("+id_kierowcy+", '"+data+"', "+oplata+", "
				+pkt_karne+","+id_historii+" );";
		update(con,sql);
	}
	
	static void removeMember(Connection con, int fineID){
		String sql = "DELETE from mandat WHERE id_mandatu = "+fineID+";";
		update(con,sql);
	}
	
	DefaultTableModel getTableFine(Connection con){
		String sql = "SELECT * FROM mandat;";
		DefaultTableModel table = getTable(con, sql); 
		//nie jestem pewna czy sie dobrze tworzy
		//do wykorzystania przez JTable
		return table;
	}
	
	static List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM mandat;";
		return getTableAsList(con, sql); 
	}
}
