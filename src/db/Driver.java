package db;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class Driver extends Manager{
	
	int id_kierowcy;
	String imie;
	String nazwisko;
	int PESEL;
	char kat_prawa_jazdy;
	
	Driver(){
		
	}

	void createTable(Connection con){
		String sql = "CREATE TABLE kierowca " +
				"(id_kierowcy INT PRIMARY KEY     NOT NULL," +
				" imie           TEXT    NOT NULL, " +
				" nazwisko       TEXT    NOT NULL, " +
				" PESEL          BIGINT, " +
				" kat_prawa_jazdy        CHAR)";
		update(con,sql);
		System.out.println("Table created successfully");
	}
	
	DefaultTableModel getTableDriver(Connection con){
		String sql = "SELECT * FROM users_info;";
		DefaultTableModel table = getTable(con, sql); 
		//nie jestem pewna czy sie dobrze tworzy
		//do wykorzystania przez JTable
		return table;
	}
	
	void addMember(Connection con, String name, String lastname,
			int pesel, char drivingLicence){
		String sql = "INSERT INTO kierowca (imie,nazwisko,PESEL,kat_prawa_jazdy)"
	            + "VALUES ("+name+", "+lastname+", "+pesel+", "+drivingLicence+" );";
		update(con,sql);
	}
	
	void removeMember(Connection con, int driverID){
		String sql = "DELETE from kierowca where id_kierowcy = "+driverID+";";
		update(con,sql);
	}
	
	String[] getDriverWithMaxKm(Connection con,String DATA1, String DATA2){
		String sql ="SELECT kr.id_kierowcy"+
					",kr.imie"+
					",kr.nazwisko"+
					",sum(hp.liczba_km) AS max_liczba_km"+

					"FROM kierowca kr"+
					"LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy"+
					"and hp.data > " + DATA1 + " and hp.data < " + DATA2 +
					"GROUP BY kr.id_kierowcy"+
					"ORDER BY sum(hp.liczba_km) DESC"+
					"LIMIT 1 ;";
		String[] elems = {"kr.id_kierowcy","kr.imir","kr.nazwisko",
				"sum(hp.liczba_km)"};
		return ask(con, sql, elems);
	}
	
	String[] getDriverWithMinKm(Connection con, String DATA1, String  DATA2){
		String sql = "SELECT kr.id_kierowcy"+
				   ",kr.imie"+
				   ",kr.nazwisko"+
				   ",sum(hp.liczba_km) AS min_liczba_km"+

				"FROM kierowca kr"+
				"LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy"+
				"and hp.data > " + DATA1 + " and hp.data < " + DATA2 + 
				"GROUP BY kr.id_kierowcy"+
				"ORDER BY sum(hp.liczba_km) ASC"+
				"LIMIT 1 ;";
		String[] elems = {"kr.id_kierowcy","kr.imir","kr.nazwisko",
		"sum(hp.liczba_km)"};
		return ask(con, sql, elems);
	}
	
    public static void main(String[] args) {
    	//Podane argumenty to: host, uzytkownik, nazwa bazy, haslo
        Manager m = new Manager();
        Connection con = m.connect("localhost","users","postgres","corewars");
        String[] elems = {"login","password"};
        m.ask(con, "SELECT login,password FROM users_info WHERE user_id=3;",elems);
        m.closeConnection(con);
    }
	
}
