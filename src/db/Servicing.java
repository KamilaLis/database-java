package db;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Servicing extends Table{

	static void addMember(Connection con, 
			int id_serwisu,int id_przejazdu,
			Date data,int id_przedmiotu, 
			int id_czynnosci,
			String miejsce_serwisu, float cena){
		String sql = "INSERT INTO serwis "+
			"(id_serwisu,id_przejazdu,data,id_przedmiotu,id_czynnosci," +
			"miejsce_serwisu,cena)" +
	         "VALUES ("+id_serwisu+", "+id_przejazdu+","+data+", "+id_przedmiotu+"," +
	         id_czynnosci+",'"+miejsce_serwisu+"',"+cena+"); ";
		update(con,sql);
	}
	
	static void removeMember(Connection con, int serviceID){
		String sql = "DELETE from serwis WHERE id_serwisu = "+serviceID+";";
		update(con,sql);
	}
	
	DefaultTableModel getTableServicing(Connection con){
		String sql = "SELECT * FROM serwis;";
		DefaultTableModel table = getTable(con, sql); 
		return table;
	}
	
	static List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM serwis;";
		return getTableAsList(con, sql); 
	}
	
	// czy jako typ moze byc podany string??
	static String[] getMostExpensiveMaintenance(Connection con, Date from, Date to, String type){
		//najdroższe serwisowanie po marce
		String sql = "SELECT mk.opis_marki " +
				",sum(sw.cena) AS cena_serwisu " +
				" FROM serwis sw " +
				" LEFT JOIN pojazd pj ON sw.id_pojazdu = pj.id_pojazdu " +
				" LEFT JOIN typ_pojazdu tp ON pj.id_typ_pojazdu = tp.id_typ_pojazdu " +
				" and tp.opis_typ = '" + type +
			    "' LEFT JOIN marka mk ON pj.id_marki = mk.id_marki " +
			    " WHERE sw.data> '" + from + "' and sw.data< '" + to + 
			    "' and tp.id_typ_pojazdu is not null " +
			    " GROUP BY mk.id_marki " +
			    " HAVING sum(sw.cena)>=0 " +
			    " ORDER BY sum(sw.cena) DESC " +
			    " LIMIT 1 ;";
		String[] elems = {"opis_marki","cena_serwisu"};
		return ask(con, sql, elems);
	}
}
