package db;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class History extends Table{

	void addMember(Connection con, int id_kierowcy,
			int id_pojazdu, String miejsce_startu,
			String miejsce_docelowe, float liczba_km,
			float srednie_zuzycie_paliwa,
			float cena_zużytego_paliwa,
			Date data, boolean przyczepa,
			int id_przyczepy ){
		String sql = "INSERT INTO historia_przejazdu "+
			"(id_kierowcy," +
			"id_pojazdu,miejsce_startu," +
			"miejsce_docelowe, liczba_km, cena_zużytego_paliwa," +
			"data_startu,data_dotarcia,id_przyczepy)"
	        + "VALUES ("+id_kierowcy+", "+id_pojazdu+", '"+miejsce_startu+"', '"
				+miejsce_docelowe+"',"+liczba_km+","+srednie_zuzycie_paliwa+","+cena_zużytego_paliwa+","+
				data+","+przyczepa+","+id_przyczepy+" );";
		update(con,sql);
		//zwieksz liczbe przejechanych km przez samochod
		(new Vehicle()).updateRun(con,liczba_km, id_pojazdu);
		//uwzglednij zuzyte paliwo (?)
	}
	
	void removeMember(Connection con, int historyID){
		String sql = "DELETE from historia_przejazdu WHERE id_historii = "+historyID+";";
		update(con,sql);
	}
	
	List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM historia_przejazdu;";
		return getTableAsList(con, sql); 
	}
}
