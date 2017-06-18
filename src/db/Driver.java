package db;
import java.sql.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Driver extends Table{

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
		String sql = "SELECT * FROM kierowca;";
		DefaultTableModel table = getTable(con, sql); 
		//nie jestem pewna czy sie dobrze tworzy
		//do wykorzystania przez JTable
		//table.setModel(model);
		return table;
	}
	
	List<String[]> getTable(Connection con){
		String sql = "SELECT * FROM kierowca;";
		return getTableAsList(con, sql); 
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
	
	String[] getDriverWithMaxKm(Connection con,Date from, Date to){
		//kierowca, ktory przejechal najwiecej
		String sql ="SELECT kr.id_kierowcy"+
					",kr.imie"+
					",kr.nazwisko"+
					",sum(hp.liczba_km) AS max_liczba_km"+

					"FROM kierowca kr"+
					"LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy"+
					"and hp.data > " + from + " and hp.data < " + to +
					"GROUP BY kr.id_kierowcy"+
					"ORDER BY sum(hp.liczba_km) DESC"+
					"LIMIT 1 ;";
		String[] elems = {"kr.id_kierowcy","kr.imir","kr.nazwisko",
				"sum(hp.liczba_km)"};
		return ask(con, sql, elems);
	}
	
	String[] getDriverWithMinKm(Connection con, Date from, Date to){
		//kierowca, ktory przejechał najmniej
		String sql = "SELECT kr.id_kierowcy"+
				   ",kr.imie"+
				   ",kr.nazwisko"+
				   ",sum(hp.liczba_km) AS min_liczba_km"+

				"FROM kierowca kr"+
				"LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy"+
				"and hp.data > " + from + " and hp.data < " + to + 
				"GROUP BY kr.id_kierowcy"+
				"ORDER BY sum(hp.liczba_km) ASC"+
				"LIMIT 1 ;";
		String[] elems = {"kr.id_kierowcy","kr.imir","kr.nazwisko",
		"sum(hp.liczba_km)"};
		return ask(con, sql, elems);
	}
	
	String[] getDriverWithCheapestCarForServicing(Connection con, Date from, Date to){
		//Kierowca z najtanszym samochodem do serwisowania w danym czasie
		//TODO: w jakim formacie przedstawiamy date?
		String sql = "SELECT kr.imie" +
				",kr.nazwisko" +
				",sum(sw.cena) AS min_cena " +
				"FROM kierowca kr  " +
				"LEFT JOIN pojazd pj ON kr.id_pojazdu = pj.id_pojazdu " +
				"LEFT JOIN serwis sw ON pj.id_pojazdu = sw.id_pojazdu " +
				"WHERE sw.data>" + from + " and sw.data< " + to + 
				"GROUP BY kr.id_kierowcy " +
				"ORDER BY sum(sw.cena) ASC " +
				"LIMIT 1 ;";
		String[] elems = {"kr.imie","kr.nazwisko","sum(sw.cena)"};
		return ask(con, sql, elems);
	}
	
	String[] getDriverWithTheMostExpensiveMaterials(Connection con){
		//kierowca z najdroższym zużyciem materiałów
		String sql = "SELECT kr.imie " +
				",kr.nazwisko " +
				",sum(hp.cena_zuzytego_paliwa) AS max_cena_paliwa " +
				"FROM kierowca kr  " +
				"LEFT JOIN historia_przejazdu hp ON kr.id_kierowcy = hp.id_kierowcy " +
				"GROUP BY kr.id_kierowcy " +
				"ORDER BY sum(hp.cena_zuzytego_paliwa) DESC " +
				"LIMIT 1 ;";
		String[] elems = {"kr.imie","kr.nazwisko","sum(hp.cena_zuzytego_paliwa)"};
		return ask(con, sql, elems);
	}
	
	String[] getDriverWithMostPenaltyPoints(Connection con, Date from, Date to){
		//Kto ma najwięcej punktów karnych przyznanych w okresie od ... do...
		String sql = "SELECT kr.id_kierowcy " +
				",kr.imie " +
				",kr.nazwisko " +
				",sum(m.pkt_karne) AS max_punkty_karne " +
				"FROM kierowca kr " +
				"LEFT JOIN mandat m ON kr.id_kierowcy = m.id_kierowcy " +
				"and m.data > " + from + " and m.data < " + to + 
				"GROUP BY kr.id_kierowcy " +
				"ORDER BY sum(m.pkt_karne) DESC " +
				"LIMIT 1 ;";
		String[] elems = {"kr.id_kierowcy","kr.imie","kr.nazwisko","sum(m.pkt_karne)"};
		return ask(con, sql, elems);
	}
	
	String[] getDriverWithLeastPenaltyPoints(Connection con, Date from, Date to){
		//Kto ma najmniej punktów karnych przyznanych w okresie od ... do...
		String sql = "SELECT kr.id_kierowcy " +
				",kr.imie " +
				",kr.nazwisko " +
				",sum(m.pkt_karne) AS max_punkty_karne " +
				"FROM kierowca kr " +
				"LEFT JOIN mandat m ON kr.id_kierowcy = m.id_kierowcy " +
				"and m.data > " + from + " and m.data < " + to + 
				"GROUP BY kr.id_kierowcy " +
				"ORDER BY sum(m.pkt_karne) ASC " +
				"LIMIT 1 ;";
		String[] elems = {"kr.id_kierowcy","kr.imie","kr.nazwisko","sum(m.pkt_karne)"};
		return ask(con, sql, elems);
	}
	
	String[] getDriverWithWrongDrivingLicense(Connection con){
		//Samochody ciezarowe bez uprawnien
		//(na razie wszystkie, w razie czego można ograniczyć tylko do ciezarowych)
		String sql = "SELECT kr.imie" +
				",kr.nazwisko" +
				"FROM kierowca kr " +
				"LEFT JOIN pojazd pj ON kr.id_pojazdu = pj.id_pojazdu" +
				"WHERE pj.wymagana_kat_pj > kr.id_kat_prawa_jazdy ;";
		String[] elems = {"kr.imie","kr.nazwisko"};
		return ask(con, sql, elems);
	}
	
	
    public static void main(String[] args) {
    	//Podane argumenty to: host, uzytkownik, nazwa bazy, haslo
        Manager m = new Manager();
        Connection con = m.connect("localhost","users","postgres","corewars");
        //String[] elems = {"login","password"};
        //DefaultTableModel users = m.getTable(con, "SELECT * FROM users_info;");
        Table t = new Table();
        List<String[]> response = t.getTableAsList(con, "SELECT * FROM users_info;");
        for( String[] row: response ){
            for( String s: row ){
                System.out.print( " " + s );
            }
            System.out.println();
        }
        System.out.println(response.get(0)[1]);
        //users.setColumnIdentifiers(new String[] {"userID", "Value"});
        //System.out.println(users.getColumnName(0));
        //m.ask(con, "SELECT login,password FROM users_info WHERE user_id=3;",elems);
        m.closeConnection(con);
    }
	
}
