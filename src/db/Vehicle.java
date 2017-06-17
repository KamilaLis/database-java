package db;

import java.sql.Connection;
import java.sql.Date;

import javax.swing.table.DefaultTableModel;

public class Vehicle{
	Manager m;
	Vehicle(){
		this.m = new Manager();
	}

	void addMember(Connection con, int id_typ_pojazdu, int id_marki,
			int id_modelu, char wymagana_kat_prawa_jazdy, float przebieg,
			String rodz_paliwa, String nr_rejestracyjny ){//rodz_paliwa????
		String sql = "INSERT INTO pojazd "+
			"(id_typ_pojazdu,id_marki," +
			"id_modelu,wymagana_kat_prawa_jazdy," +
			"przebieg, rodz_paliwa, nr_rejestracyjny)"
	        + "VALUES ("+id_typ_pojazdu+", "+id_marki+", "+id_modelu+", "
				+wymagana_kat_prawa_jazdy+","+przebieg+","+rodz_paliwa+","+nr_rejestracyjny+" );";
		m.update(con,sql);
	}
	
	void removeMember(Connection con, int vehicleID){
		String sql = "DELETE from pojazd WHERE id_pojazdu = "+vehicleID+";";
		m.update(con,sql);
	}
	
	DefaultTableModel getTableVehicle(Connection con){
		String sql = "SELECT * FROM pojazdy;";
		DefaultTableModel table = m.getTable(con, sql); 
		//nie jestem pewna czy sie dobrze tworzy
		//do wykorzystania przez JTable
		return table;
	}
	
	void updateRun(Connection con, float km, int vehicleID){
		// Dodanie km do przebiegu (po wpisaniu nowej historii przejazdu)
		String sql = "SELECT przebieg FROM pojazd WHERE id_pojazdu="+vehicleID+";";
		String[] elems = {"przebieg"};
		String[] oldRun = m.ask(con, sql, elems);
		float newRun = km+Float.parseFloat(oldRun[0]);
		sql = "UPDATE pojazd SET przebieg = "+newRun+" WHERE id_pojazdu="+vehicleID+";";
		m.update(con, sql);
	}
	
	String[] getVehicleWithMaxFuelConsumption(Connection con, Date from, Date to){
		String sql = "SELECT pj.id_pojazdu" +
				",avg(hp.srednie_zuzycie_paliwa) AS srednie_paliwo" +
				" FROM pojazd pj " +
				"LEFT JOIN historia_przejazdu hp ON pj.id_pojazdu = hp.id_pojazdu " +
				"and hp.data > " + from + " and hp.data < " + to + 
				"GROUP BY pj.id_pojazdu " +
				"ORDER BY avg(hp.srednie_zuzycie_paliwa) DESC " +
				"LIMIT 1 ;";
		String[] elems = {"pj.id_pojazdu","hp.srednie_zuzycie_paliwa"};
		return m.ask(con, sql, elems);
	}
}
