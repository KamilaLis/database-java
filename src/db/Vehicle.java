package db;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;


public class Vehicle extends Table{

	static void addMember(Connection con, int id_typ_pojazdu,
			int id_marki,int id_modelu, 
			int id_kat_prawa_jazdy, float przebieg,
			String rodz_paliwa, String nr_rejestracyjny ){
		String sql = "INSERT INTO pojazd "+
			"(id_typ_pojazdu,id_marki," +
			"id_modelu,wymagana_kat_p_j," +
			"przebieg, rodz_paliwa, nr_rejestracyjny)"
	        + "VALUES ("+id_typ_pojazdu+", "+id_marki+", "+id_modelu+","
				+id_kat_prawa_jazdy+","+przebieg+",'"+rodz_paliwa+"','"+nr_rejestracyjny+"' );";
		update(con,sql);
	}
	
	static void removeMember(Connection con, int vehicleID){
		String sql = "DELETE from pojazd WHERE id_pojazdu = "+vehicleID+";";
		update(con,sql);
	}
	
	static List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM pojazd;";
		return getTableAsList(con, sql); 
	}
	
	void updateRun(Connection con, float km, int vehicleID){
		// Dodanie km do przebiegu (po wpisaniu nowej historii przejazdu)
		String sql = "SELECT przebieg FROM pojazd WHERE id_pojazdu="+vehicleID+";";
		String[] elems = {"przebieg"};
		String[] oldRun = ask(con, sql, elems);
		float newRun = km+Float.parseFloat(oldRun[0]);
		sql = "UPDATE pojazd SET przebieg = "+newRun+" WHERE id_pojazdu="+vehicleID+";";
		update(con, sql);
	}
	
	static String[] getVehicleWithMaxFuelConsumption(Connection con){
		String sql = "SELECT pj.id_pojazdu, m.opis_marki, mo.opis_modelu " +
				",avg(hp.srednie_zuzycie_paliwa) AS srednie_paliwo" +
				" FROM pojazd pj " +
				" LEFT JOIN historia_przejazdu hp ON pj.id_pojazdu = hp.id_pojazdu " +
				" LEFT JOIN marka m ON pj.id_marki = m.id_marki " + 
				" LEFT JOIN model mo ON pj.id_modelu = mo.id_model " +
				" GROUP BY pj.id_pojazdu, m.opis_marki, mo.opis_modelu " +
				" HAVING avg(hp.srednie_zuzycie_paliwa)>=0 " + 
				" ORDER BY avg(hp.srednie_zuzycie_paliwa) DESC " +
				" LIMIT 1 ;";
		String[] elems = {"id_pojazdu","opis_marki","opis_modelu","srednie_paliwo"};
		return ask(con, sql, elems);
	}
}
